package com.jeesd.sys.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * OAuth安全工具类
 */
@UtilityClass
public class SecurityUtils {

    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {

        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户名
     */
    public String getUserName(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof String) {
            return (String) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public String getUserName() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUserName(authentication);
    }
}
