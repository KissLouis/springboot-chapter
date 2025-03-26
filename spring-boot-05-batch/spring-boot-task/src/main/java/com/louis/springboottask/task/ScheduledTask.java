package com.louis.springboottask.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ScheduledTask {

    /**
     * 固定速率：每隔5秒执行一次（从任务开始时间计算）
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRateTask() {
        System.out.println("[FixedRate] 当前时间：" + LocalDateTime.now());
    }

    /**
     * 固定延迟：上一次任务结束后，延迟3秒执行
     */
    @Scheduled(fixedDelay = 3000)
    public void fixedDelayTask() {
        try {
            Thread.sleep(2000); // 模拟任务耗时2秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[FixedDelay] 当前时间：" + LocalDateTime.now());
    }

    /**
     * Cron表达式：每分钟的第10秒执行
     */
    @Scheduled(cron = "10 * * * * ?")
    public void cronTask() {
        System.out.println("[Cron] 当前时间：" + LocalDateTime.now());
    }
}