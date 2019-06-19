package com.jeesd.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jeesd.sys.domain.UserModel;
import com.jeesd.sys.dto.UserDto;
import com.jeesd.sys.dto.UserInfo;
import com.jeesd.sys.dto.UserTreeDto;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author song
 */
public interface IUserService extends IService<UserModel> {

    /**
     * 通过id查询用户
     * @param userId
     * @return
     */
    UserModel getUserById(Long userId);

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    UserDto getUserByName(String username);

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    UserInfo getUserInfoByName(String username);

    /**
     * 通过用户id查询用户角色权限
     * @param userId
     * @return
     */
    UserTreeDto getUserDtoById(Long userId);

    /**
     * 分页查询用户
     * @param page
     * @param userModel
     * @return
     */
    IPage getUsersPage(Page page, UserModel userModel);

    /**
     * 添加用户
     * @param userModel
     * @return
     */
    boolean saveUser(UserModel userModel);
}
