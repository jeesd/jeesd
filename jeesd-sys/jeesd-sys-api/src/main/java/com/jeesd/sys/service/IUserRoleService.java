package com.jeesd.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jeesd.sys.domain.UserRoleModel;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author song
 */
public interface IUserRoleService extends IService<UserRoleModel> {

    /**
     * 添加用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveUserRoles(Long userId, Long[] roleIds);
}
