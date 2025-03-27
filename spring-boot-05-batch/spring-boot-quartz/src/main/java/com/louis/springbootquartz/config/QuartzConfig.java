package com.louis.springbootquartz.config;

import com.louis.springbootquartz.job.SimpleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    // 定义JobDetail
    @Bean
    public JobDetail simpleJobDetail() {
        return JobBuilder.newJob(SimpleJob.class)
                .withIdentity("simpleJob") // 任务唯一标识
                .storeDurably() // 持久化存储
                .build();
    }

    // 定义Trigger
    @Bean
    public Trigger simpleJobTrigger() {
        SimpleScheduleBuilder schedule = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5) // 每5秒执行一次
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(simpleJobDetail())
                .withIdentity("simpleTrigger")
                .withSchedule(schedule)
                .build();
    }
}