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
 * 组织机构实体
 * </p>
 *
 * @author song
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_org")
public class OrgModel implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  主键 ID
     */
    private Long id;
    /**
     *  父ID
     */
    private Long parentId;
    /**
     *  名称
     */
    private String name;
    /**
     *  排序
     */
    private Integer sort;
    /**
     *  状态 0、启用 1、禁用
     */
    private Integer status;
    /**
     *  备注
     */
    private String remark;
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