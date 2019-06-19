package com.jeesd.auth.fegin;

import com.jeesd.sys.feign.UserFeignService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "jeesd-sys-service")
public interface UserFeignClient extends UserFeignService {

}
