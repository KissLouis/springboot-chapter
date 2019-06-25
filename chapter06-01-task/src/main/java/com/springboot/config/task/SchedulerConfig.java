package com.springboot.config.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @param
 * @author Louis
 * @description: //TODO 多定时任务的并行调度
 * @date 2019/6/23 18:20
 * @return
 */
@EnableScheduling   //启用定时任务
@Configuration
public class SchedulerConfig  {


    /**
     * @param []
     * @return org.springframework.scheduling.TaskScheduler
     * @description: //TODO task线程池
     * @author Louis
     * @date 2019/6/23 18:13
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);// 配置线程池大小，根据任务数量定制
        scheduler.setThreadNamePrefix("spring-task-scheduler-thread-");//线程名字前缀
        scheduler.setAwaitTerminationSeconds(60);// 线程池关闭前最大等待时间，确保最后一定关闭
        scheduler.setWaitForTasksToCompleteOnShutdown(true);// 线程池关闭时等待所有任务完成
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());// 任务丢弃策略
        return scheduler;
    }

}
