package com.jeesd.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心（暂未使用）
 * 使用config配置需要把配置文件名称改为bootstrap.yml,程序引导时加载
 */
@EnableConfigServer
@SpringCloudApplication
public class JeesdConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeesdConfigApplication.class, args);
    }
}
