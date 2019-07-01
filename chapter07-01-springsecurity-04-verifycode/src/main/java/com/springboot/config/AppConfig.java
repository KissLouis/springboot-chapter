package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;

/**
 * @author Louis
 * @title: AppConfig
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/7/1 20:15
 */
@Configuration
public class AppConfig {

    @Bean("sessionStrategy")
    public SessionStrategy registBean() {
        SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
        return sessionStrategy;
    }

}
