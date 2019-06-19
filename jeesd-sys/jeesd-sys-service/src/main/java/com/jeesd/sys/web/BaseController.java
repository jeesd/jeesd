package com.jeesd.sys.web;


import com.jeesd.sys.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public String getUsername(HttpServletRequest request) {

        String username = SecurityUtils.getUserName();
        if(StringUtils.isBlank(username)) {
           return "";
        }
        return username;
    }
}
