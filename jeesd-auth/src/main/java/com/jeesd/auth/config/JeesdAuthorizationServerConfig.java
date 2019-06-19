package com.jeesd.auth.config;

import com.jeesd.auth.service.JeesdUserDetailService;
import com.jeesd.auth.service.JeesdClientDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class JeesdAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final JeesdUserDetailService userDetailsService;
    private final RedisConnectionFactory redisConnectionFactory;
    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.withClientDetails(new JeesdClientDetailService(dataSource));
       /* //使用内存存储
        clients.inMemory()
                //分配客户端账号
                .withClient("test")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("auth")
                .authorities("oauth2")
                .secret("e10adc3949ba59abbe56e057f20f883e")
                .and()
                .withClient("jeesd")
                .secret(new BCryptPasswordEncoder().encode("e10adc3949ba59abbe56e057f20f883e"))
                //支持的授权类型
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("auth")
                //token有效时长
                .accessTokenValiditySeconds(7200)
                //refreshToken有效时长
                .refreshTokenValiditySeconds(50000);*/
    }

    //使用JWT作为token
   /* @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }*/

    /*@Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //设置签名密钥
        jwtAccessTokenConverter.setSigningKey("jeesdTest");
        return jwtAccessTokenConverter;
    }*/

    @Bean
    public TokenStore tokenStore() {

        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancer())
                //.accessTokenConverter(jwtAccessTokenConverter())
                .reuseRefreshTokens(true)
                //配置以生效password模式
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(1);
            User user = (User)authentication.getPrincipal();
            additionalInfo.put("username", user.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
