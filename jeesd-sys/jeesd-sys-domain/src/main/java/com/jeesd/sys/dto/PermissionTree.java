package com.jeesd.sys.dto;

import com.jeesd.common.dto.TreeNode;
import com.jeesd.sys.domain.ResourceModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionTree extends TreeNode implements Serializable {

    /**
     * 名称
     */
    private String title;

    private String name;

    private String permission;
    /**
     * 图标
     */
    private String icon;
    /**
     * 节点是否选中
     */
    private boolean selectable;
    /**
     * id
     */
    private Long key;

    public PermissionTree() {
    }

    public PermissionTree(ResourceModel model) {
        this.icon = model.getIcon();
        this.id = model.getId();
        this.key = model.getId();
        this.name = model.getName();
        this.parentId = model.getParentId();
        this.title = model.getName();
        this.permission = model.getPermission();

    }

}
