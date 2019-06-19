package com.jeesd.auth.config;

import com.jeesd.auth.service.JeesdUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class JeesdWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JeesdUserDetailService userDetailService;
    //@Autowired
    //@Lazy
    //private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;
    //@Autowired
    //@Lazy
    //private UserAuthenticationFailHandler userAuthenticationFailHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //{bcrypt}{noop}加密方式要一致，需要和数据库密码对比
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录,loginPage为登录请求的url,loginProcessingUrl为表单登录处理的URL
        http    //.formLogin()
                    //.loginProcessingUrl("/form/login")
                    //.usernameParameter("username")
                    //.passwordParameter("password")
                   //登录成功之后的处理
                   //.successHandler(userAuthenticationSuccessHandler)
                   //.failureHandler(userAuthenticationFailHandler)
                //.and()
                .authorizeRequests()
                    .antMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()//任意请求需要登录
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
