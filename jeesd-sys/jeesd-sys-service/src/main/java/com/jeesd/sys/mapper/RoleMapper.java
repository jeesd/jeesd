package com.jeesd.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeesd.sys.domain.RoleModel;
import com.jeesd.sys.dto.RoleDto;
import com.jeesd.sys.dto.RoleTreeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author song
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleModel> {

    /**
     * 通过用户id查询角色列表
     * @param userId
     * @return
     */
    List<RoleDto> queryRoleByUserId(Long userId);
    /**
     * 通过用户id查询角色列表
     * @param userId
     * @return
     */
    List<RoleTreeDto> getRoleByUserId(Long userId);

    /**
     * 分页查询角色列表
     * @param page
     * @param role
     * @return
     */
    IPage<List<RoleModel>> getRolesPage(Page page, @Param("role") RoleModel role);
}
