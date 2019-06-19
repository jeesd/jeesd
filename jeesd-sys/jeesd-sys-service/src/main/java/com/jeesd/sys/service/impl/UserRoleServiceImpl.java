package com.jeesd.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeesd.sys.domain.UserRoleModel;
import com.jeesd.sys.mapper.UserRoleMapper;
import com.jeesd.sys.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author song
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleModel> implements IUserRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUserRoles(Long userId, Long[] roleIds) {

        if(roleIds == null || roleIds.length <= 0) {
            return true;
        }
        this.remove(Wrappers.<UserRoleModel>query().lambda()
                .eq(UserRoleModel::getUserId, userId));
        List<UserRoleModel> userRoleList = Arrays.stream(roleIds)
                .map(roleId -> {
                    UserRoleModel model =  new UserRoleModel();
                    model.setUserId(userId);
                    model.setRoleId(roleId);
                    return model;
                }).collect(Collectors.toList());

        return this.saveBatch(userRoleList);
    }
}
