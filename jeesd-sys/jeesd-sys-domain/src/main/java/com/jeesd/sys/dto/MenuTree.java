package com.jeesd.sys.dto;

import com.jeesd.common.dto.TreeNode;
import com.jeesd.sys.domain.ResourceModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends TreeNode implements Serializable {

    /**
     * 名称
     */
    private String name;
    /**
     * 图标
     */
    private String icon;
    /**
     * 类型（类型 0、菜单 1、按钮）
     */
    private Integer type;
    /**
     * 状态（状态 -1、禁用 0、正常）
     */
    private Integer status;
    /**
     * url
     */
    private String path;
    /**
     * 组件名称
     */
    private String component;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 标签名称
     */
    private String title;

    public MenuTree() {
    }

    public MenuTree(ResourceModel model) {
        this.icon = model.getIcon();
        this.id = model.getId();
        this.name = model.getName();
        this.parentId = model.getParentId();
        this.type = model.getType();
        this.status = model.getType();
        this.path = model.getPath();
        this.component = model.getComponent();
        this.sort = model.getSort();
        this.permission = model.getPermission();
        this.title = model.getName();
    }

}
