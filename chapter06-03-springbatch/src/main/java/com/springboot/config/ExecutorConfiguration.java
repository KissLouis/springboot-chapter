package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * @类描述：配置TaskExecutor
 * @项目名称：chapter08-03-springbatch @包名： com.springboot.config
 * @类名称：ExecutorConfiguration
 * @创建人：Louis
 * @创建时间：2019年7月11日下午4:30:41
 */
@Configuration
public class ExecutorConfiguration {

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(50);
		threadPoolTaskExecutor.setMaxPoolSize(200);
		threadPoolTaskExecutor.setQueueCapacity(1000);
		threadPoolTaskExecutor.setThreadNamePrefix("Data-Job");
		return threadPoolTaskExecutor;
	}

}
