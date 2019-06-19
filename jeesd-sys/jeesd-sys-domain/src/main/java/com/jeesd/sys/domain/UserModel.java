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
 * 用户实体
 * </p>
 *
 * @author song
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class UserModel implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     *  主键ID
     */
    private Long id;
    /**
     *  用户名
     */
    private String username;
    /**
     *  密码
     */
    private String password;
    /**
     *  盐
     */
    private String salt;
    /**
     *  昵称
     */
    private String nickName;
    /**
     *  部门ID
     */
    private Long deptId;
    /**
     *  头像
     */
    private String headPicture;
    /**
     *  性别
     */
    private String sex;
    /**
     *  手机号
     */
    private String phone;
    /**
     *  邮箱
     */
    private String email;
    /**
     *  状态 2、冻结 3、删除 0、注册 1、正常
     */
    private Integer status;
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