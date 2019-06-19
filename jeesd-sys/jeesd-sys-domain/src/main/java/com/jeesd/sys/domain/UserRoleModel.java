package com.jeesd.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户角色实体
 * </p>
 *
 * @author song
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user_role")
public class UserRoleModel implements Serializable{

    private static final long serialVersionUID=1L;

    /**
     *  主键ID
     */
    private Long id;
    /**
     *  用户ID
     */
    private Long userId;
    /**
     *  角色ID
     */
    private Long roleId;

}