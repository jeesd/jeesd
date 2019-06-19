package com.jeesd.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeesd.sys.domain.RoleResourceModel;
import com.jeesd.sys.mapper.RoleResourceMapper;
import com.jeesd.sys.service.IRoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色资源表 服务实现类
 * </p>
 *
 * @author song
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResourceModel> implements IRoleResourceService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleResource(Long roleId, Long[] permissionIds) {

        if(permissionIds == null || permissionIds.length <= 0) {
            return true;
        }
        this.remove(Wrappers.<RoleResourceModel>query().lambda()
            .eq(RoleResourceModel::getRoleId, roleId));
        List<RoleResourceModel> roleResourceList = Arrays.stream(permissionIds)
                .map(permissionId -> {
                   RoleResourceModel model =  new RoleResourceModel();
                   model.setRoleId(roleId);
                   model.setResourceId(permissionId);
                   return model;
                }).collect(Collectors.toList());

        return this.saveBatch(roleResourceList);
    }
}
