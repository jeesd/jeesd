package com.jeesd.auth.service;
import com.jeesd.auth.fegin.UserFeignClient;
import com.jeesd.sys.dto.PermissionDto;
import com.jeesd.sys.dto.RoleDto;
import com.jeesd.sys.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class JeesdUserDetailService implements UserDetailsService {

    @Autowired
    private UserFeignClient userClent;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserDto userDto = userClent.getUserDtoByName(userName);
        if (userDto == null) {
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // true:可用 false:不可用
        final boolean enabled = true;
        // true:没过期 false:过期
        final boolean accountNonExpired = true;
        // true:凭证有效 false:凭证无效
        final boolean credentialsNonExpired = true;
        // true:未锁定 false:已锁定
        final boolean accountNonLocked = true;
        for (RoleDto role : userDto.getRoles()) {
            //角色必须是ROLE_开头
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role.getRoleId());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            for (PermissionDto permission : role.getPermissions()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermission());
                grantedAuthorities.add(authority);
            }
        }
        return new User(userDto.getUsername(), userDto.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);

    }
}
