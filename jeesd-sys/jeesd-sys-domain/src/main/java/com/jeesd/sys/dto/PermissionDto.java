package com.jeesd.sys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PermissionDto implements Serializable {

    /**
     * 权限id
     */
    private Long permissionsId;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     *  权限标识
     */
    private String permission;
    /**
     * url路径
     */
    private String path;
}
