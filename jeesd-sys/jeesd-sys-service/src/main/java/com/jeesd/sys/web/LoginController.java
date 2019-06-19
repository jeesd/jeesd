package com.jeesd.sys.web;

import com.jeesd.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Api("用户登录模块")
@RequestMapping("/auth")
public class LoginController extends BaseController {

    @GetMapping("/user/userInfo")
    @ApiOperation(value="获取当前用户", notes="获取当前用户")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/logout")
    @ApiOperation(value="登出功能", notes="用户登出")
    public Result logout() {

        return new Result (null, "登出成功！");

    }
}
