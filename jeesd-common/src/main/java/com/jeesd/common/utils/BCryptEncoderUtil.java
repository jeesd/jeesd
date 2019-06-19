package com.jeesd.common.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@UtilityClass
public class BCryptEncoderUtil {

    public static String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
       System.out.println(BCryptEncoderUtil.encodePassword("e10adc3949ba59abbe56e057f20f883e"));
    }
}
