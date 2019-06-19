package com.jeesd.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeesd.sys.domain.RoleModel;
import com.jeesd.sys.domain.UserModel;
import com.jeesd.sys.dto.RoleTreeDto;
import com.jeesd.sys.mapper.RoleMapper;
import com.jeesd.sys.mapper.UserMapper;
import com.jeesd.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author song
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleModel> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage getRolesPage(Page page, RoleModel roleModel) {

        return roleMapper.getRolesPage(page, roleModel);
    }

    @Override
    public List<RoleTreeDto> getRoleByUser(String username) {

        UserModel user = userMapper.selectOne(Wrappers.<UserModel>query()
                .lambda().eq(UserModel::getUsername, username));
        if(user == null) {
            return null;
        }
        return roleMapper.getRoleByUserId(user.getId());
    }
}
