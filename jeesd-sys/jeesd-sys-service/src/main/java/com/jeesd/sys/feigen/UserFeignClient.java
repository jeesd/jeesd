package com.jeesd.sys.feigen;

import com.jeesd.sys.feign.UserFeignService;
import com.jeesd.sys.domain.UserModel;
import com.jeesd.sys.dto.UserDto;
import com.jeesd.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/user")
public class UserFeignClient implements UserFeignService {

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping(value = "/id")
    public UserModel getUserDtoById(Long userId) {

        return userService.getUserById(userId);
    }

    @Override
    @GetMapping(value = "/name")
    public UserDto getUserDtoByName(@RequestParam("username") String username) {

        return userService.getUserByName(username);
    }
}
