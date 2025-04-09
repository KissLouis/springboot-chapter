package com.louis.springbootredisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host}")
    private String host;


    @Value("${spring.data.redis.port}")
    private String port;


    @Value("${spring.data.redis.password}")
    private String password;



    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + System.getProperty("spring.redis.host", host) + ":" + System.getProperty("spring.redis.port", port))
                .setPassword(System.getProperty("spring.redis.password", password));
        return Redisson.create(config);
    }
}