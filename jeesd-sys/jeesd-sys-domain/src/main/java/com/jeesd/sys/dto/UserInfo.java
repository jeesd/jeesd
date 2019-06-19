package com.jeesd.sys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserInfo implements Serializable {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private  String password;

    /**
     * 角色列表
     */
    private List<Long> roles;

    /**
     * 权限标识列表
     */
    private Set<String> permissions;
}
