package com.jeesd.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeesd.sys.domain.ResourceModel;
import com.jeesd.sys.dto.RoleTreeDto;
import com.jeesd.sys.mapper.ResourceMapper;
import com.jeesd.sys.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/**
 * <p>
 * 菜单资源表 服务实现类
 * </p>
 *
 * @author song
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, ResourceModel> implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<ResourceModel> queryMenuByRoleId(Long roleId) {

        return resourceMapper.queryMenuByRoleId(roleId);
    }

    @Override
    public Set<ResourceModel> getMenusByRoles(List<RoleTreeDto> roles) {

        return resourceMapper.getMenusByRoles(roles);
    }
}
