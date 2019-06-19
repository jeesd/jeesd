package com.jeesd.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jeesd.sys.domain.RoleResourceModel;

/**
 * <p>
 * 角色资源表 服务类
 * </p>
 *
 * @author song
 */
public interface IRoleResourceService extends IService<RoleResourceModel> {

    /**
     * 添加或者修改角色权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRoleResource(Long roleId, Long[] permissionIds);
}
