package com.jeesd.sys.dto;

import com.jeesd.common.dto.TreeNode;
import com.jeesd.sys.domain.ResourceModel;
import lombok.Data;
import java.io.Serializable;

@Data
public class PermissionTreeDto extends TreeNode implements Serializable {

    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * url路径
     */
    private String path;
    /**
     *  类型 0、菜单 1、按钮
     */
    private Integer type;
    /**
     *  权限标识
     */
    private String permission;

    public PermissionTreeDto() {

    }

    public PermissionTreeDto(ResourceModel model) {
        this.path = model.getPath();
        this.permission = model.getPermission();
        this.permissionName = model.getName();
        this.id = model.getId();
        this.parentId = model.getParentId();
        this.type = model.getType();
    }
}
