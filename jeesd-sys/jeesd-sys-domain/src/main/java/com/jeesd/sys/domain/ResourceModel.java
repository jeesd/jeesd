package com.jeesd.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 菜单资源实体
 * </p>
 *
 * @author song
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_resource")
public class ResourceModel implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  主键ID
     */
    private Long id;
    /**
     *  菜单名称
     */
    private String name;
    /**
     *  权限标识
     */
    private String permission;
    /**
     *  URL
     */
    private String path;
    /**
     *  父ID
     */
    private Long parentId;
    /**
     *  图标
     */
    private String icon;
    /**
     *  类型 0、菜单 1、按钮
     */
    private Integer type;
    /**
     *  状态 1、禁用 0、正常
     */
    private Integer status;
    /**
     *  vue组件
     */
    private String component;
    /**
     *  排序
     */
    private Integer sort;
    /**
     *  操作人
     */
    private String operator;
    /**
     *  创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}