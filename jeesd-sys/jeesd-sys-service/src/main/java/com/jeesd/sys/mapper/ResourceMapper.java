package com.jeesd.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeesd.sys.domain.ResourceModel;
import com.jeesd.sys.dto.PermissionDto;
import com.jeesd.sys.dto.RoleTreeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单资源表 Mapper 接口
 * </p>
 *
 * @author song
 */
@Mapper
public interface ResourceMapper extends BaseMapper<ResourceModel> {

    /**
     * 通过角色id查询权限列表
     * @param roleId
     * @return
     */
    Set<PermissionDto> queryPermissionByRoleId(Long roleId);

    /**
     * 通过角色查询菜单
     * @param roleId
     * @return
     */
    List<ResourceModel> queryMenuByRoleId(Long roleId);

    /**
     * 通过角色查询权限标识
     * @param roles
     * @return
     */
   Set<String> getPermissionByRoles(@Param("roles") List<RoleTreeDto> roles);

    /**
     * 通过角色集合查询菜单
     * @param roles
     * @return
     */
   Set<ResourceModel> getMenusByRoles(@Param("roles") List<RoleTreeDto> roles);
}
