package com.jeesd.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jeesd.sys.domain.RoleModel;
import com.jeesd.sys.dto.RoleTreeDto;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author song
 */
public interface IRoleService extends IService<RoleModel> {

    /**
     * 分页查询角色
     * @param page
     * @param roleModel
     * @return
     */
    IPage getRolesPage(Page page, RoleModel roleModel);

    /**
     * 通过用户名查询角色
     * @param username
     * @return
     */
    List<RoleTreeDto>  getRoleByUser(String username);

}
