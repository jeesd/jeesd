package com.jeesd.sys.feign;

import com.jeesd.sys.domain.UserModel;
import com.jeesd.sys.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jeesd-sys-service")
public interface UserFeignService {

    /**
     * 通过用户id查询
     * @param userId
     * @return
     */
    @RequestMapping(value = "/feign/user/id",method = RequestMethod.GET)
    UserModel getUserDtoById(@RequestParam("userId") Long userId);

    /**
     * 通过用户名查询
     * @param username
     * @return
     */
    @RequestMapping(value = "/feign/user/name",method = RequestMethod.GET)
    UserDto getUserDtoByName(@RequestParam("username") String username);
}
