package com.jeesd.sys.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeesd.common.utils.Result;
import com.jeesd.sys.domain.UserModel;
import com.jeesd.sys.service.IUserRoleService;
import com.jeesd.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api("用户模块")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;

    @ApiOperation(value="获取当前用户信息", notes="获取当前用户信息")
    @GetMapping(value = "/info")
    public Result getUserInfo(HttpServletRequest request) {

        return new Result(userService.getUserInfoByName(getUsername(request)));
    }

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @GetMapping(value = "/list")
    public Result getUserList(Page page, UserModel userModel) {

        return new Result(userService.getUsersPage(page,userModel));
    }

    @ApiOperation(value="通过用户名获取用户", notes="通过用户名获取用户")
    @GetMapping(value = "/info/{username}")
    public Result getUserByName(@PathVariable String username) {
        UserModel user = userService.getOne(Wrappers.<UserModel>query()
                .lambda()
                .eq(UserModel::getUsername, username));
        if(null == user) {
            return new Result(null, "用户不存在！");
        }
        return new Result(user);
    }

    @ApiOperation(value="通过用户id获取用户", notes="通过用户id获取用户")
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Long id) {
        return new Result(userService.getUserById(id));
    }

    @ApiOperation(value="添加用户", notes="添加用户")
    @PostMapping(value = "/users")
    public Result addUser(UserModel userModel,
                     HttpServletRequest request) {
        if(null == userModel ) {
            return new Result(null, "用户参数不能空！");
        }
        userModel.setOperator("");
        return new Result(userService.saveUser(userModel));
    }

    @ApiOperation(value="修改用户", notes="修改用户")
    @PutMapping(value = "/modify")
    public Result updateUser(UserModel userModel,
                        HttpServletRequest request) {
        if(null == userModel ) {
            return new Result(null, "用户参数不能空！");
        }
        userModel.setOperator("");
        return new Result(userService.updateById(userModel));
    }

    @ApiOperation(value="删除用户", notes="删除用户")
    @DeleteMapping(value = "/{id}/del")
    public Result deleteRole(@PathVariable Long id) {

        return new Result(userService.removeById(id));
    }

    @ApiOperation(value="更新用户角色", notes="更新用户角色")
    @PostMapping(value = "/roles")
    public Result updateUserRoles(@RequestParam("userId") Long userId,
                             @RequestParam(value = "roleIds[]", required = false) Long[] roleIds) {
        return new Result(userRoleService.saveUserRoles(userId, roleIds));
    }
}
