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
 * 部门实体
 * </p>
 *
 * @author song
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dept")
public class DeptModel implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  主键ID
     */
    private Long id;
    /**
     *  部门名称
     */
    private String name;
    /**
     *  父ID
     */
    private Long parentId;
    /**
     *  状态 1、禁用 0、正常
     */
    private Integer status;
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