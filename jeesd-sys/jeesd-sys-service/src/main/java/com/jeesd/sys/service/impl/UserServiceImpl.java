package com.jeesd.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeesd.common.enums.CommonEnum;
import com.jeesd.common.utils.CryptoUtils;
import com.jeesd.common.utils.LayoutTreeUtil;
import com.jeesd.kafka.sys.dto.*;
import com.jeesd.sys.mapper.UserMapper;
import com.jeesd.sys.domain.UserModel;
import com.jeesd.sys.dto.*;
import com.jeesd.sys.mapper.ResourceMapper;
import com.jeesd.sys.mapper.RoleMapper;
import com.jeesd.sys.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author song
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public UserModel getUserById(Long userId) {

        return userMapper.selectById(userId);
    }

    @Override
    public UserDto getUserByName(String username) {

        UserDto dto = new UserDto();
        UserModel user = this.getOne(
                new QueryWrapper<UserModel>().eq("username", username)
        );
        dto.setUserId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        List<RoleDto> roles = roleMapper.queryRoleByUserId(user.getId());
        //设置权限列表
        roles.forEach(role -> {
            Set<PermissionDto> permissions = resourceMapper.queryPermissionByRoleId(role.getRoleId());
            role.setPermissions(permissions);
        });
        //设置角色列表
        dto.setRoles(roles);
        return dto;
    }

    @Override
    public UserInfo getUserInfoByName(String username) {

        UserInfo userInfo = new UserInfo();
        UserModel user = this.getOne(
                new QueryWrapper<UserModel>().eq("username", username)
        );
        userInfo.setUserId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setPassword(user.getPassword());
        //设置角色id列表
        List<Long> roleIds = roleMapper.queryRoleByUserId(user.getId())
                .stream()
                .map(RoleDto::getRoleId)
                .collect(Collectors.toList());
        userInfo.setRoles(roleIds);
        //设置权限列表
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = resourceMapper.queryPermissionByRoleId(roleId)
                    .stream()
                    .filter(resource -> StringUtils.isNotEmpty(resource.getPermission()))
                    .map(PermissionDto::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(permissions);
        return userInfo;
    }

    @Override
    public UserTreeDto getUserDtoById(Long userId) {

        UserTreeDto userDto = new UserTreeDto();
        UserModel user = this.getById(userId);
        userDto.setUserId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        List<RoleTreeDto> roles = roleMapper.getRoleByUserId(user.getId());
        userDto.setPermissions(resourceMapper.getPermissionByRoles(roles));
        //设置权限列表
        roles.forEach(role -> {
            List<PermissionTreeDto> treeList = resourceMapper.queryMenuByRoleId(role.getRoleId())
                    .stream()
                    .map(PermissionTreeDto::new)
                    .collect(Collectors.toList());
            List<PermissionTreeDto> permissionTrees = LayoutTreeUtil.buildByLoop(treeList, 0L);
            role.setPermissions(permissionTrees);
        });
        //设置角色列表
        userDto.setRoles(roles);
       return userDto;
    }

    @Override
    public IPage getUsersPage(Page page, UserModel userModel) {

        return userMapper.getUsersPage(page, userModel);
    }

    @Override
    public boolean saveUser(UserModel userModel) {
        userModel.setStatus(CommonEnum.STATE_0.getValue());
        userModel.setCreateTime(LocalDateTime.now());
        //密码加密和盐后面再加
        String salt = CryptoUtils.generateSalt();
        userModel.setSalt(salt);
        //userModel.setPassword(CryptoUtils.encodeMD5(userModel.getPassword()+salt));
        return userMapper.insert(userModel) > 0 ? true : false;
    }
}
