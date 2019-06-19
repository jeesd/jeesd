package com.jeesd.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jeesd.sys.domain.ResourceModel;
import com.jeesd.sys.dto.RoleTreeDto;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单资源表 服务类
 * </p>
 *
 * @author song
 */
public interface IResourceService extends IService<ResourceModel> {

    /**
     * 通过角色查询菜单
     * @param roleId
     * @return
     */
    List<ResourceModel> queryMenuByRoleId(Long roleId);

    /**
     * 通过角色集合查询菜单
     * @param roles
     * @return
     */
    Set<ResourceModel> getMenusByRoles(List<RoleTreeDto> roles);

    /**
     * 分页查询资源权限列表
     * @param page
     * @param resourceModel
     * @return
     */
    //IPage getResourcesPage(Page page, ResourceModel resourceModel);
}
