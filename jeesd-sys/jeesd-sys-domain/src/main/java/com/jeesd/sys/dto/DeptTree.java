package com.jeesd.sys.dto;

import com.jeesd.common.dto.TreeNode;
import com.jeesd.sys.domain.DeptModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode implements Serializable {

    /**
     *  部门名称
     */
    private String name;
    /**
     *  状态 -1、禁用 0、正常
     */
    private Integer status;
    /**
     *  排序
     */
    private Integer sort;
    /**
     *  创建时间
     */
    private LocalDateTime createTime;

    public DeptTree(DeptModel deptMode) {
        this.id = deptMode.getId();
        this.parentId = deptMode.getParentId();
        this.createTime = deptMode.getCreateTime();
        this.name = deptMode.getName();
        this.sort = deptMode.getSort();
        this.status = deptMode.getStatus();
    }
}
