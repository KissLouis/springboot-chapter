package com.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Louis
 * @title: PrintTask
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 17:34
 */
@Component
public class PrintTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @return void
     * @description: //TODO cron表达式，根据表达式循环执行
     * @author Louis
     * @date 2019/6/23 18:06
     */
    @Scheduled(cron = "*/10 * * * * ?")
    private void task01() {
        System.out.println("[" + Thread.currentThread().getName() + "]" + "-task01-每10秒执行一次-现在时间：" + dateFormat.format(new Date()));
    }

    /**
     * @param []
     * @return void
     * @description: //TODO 上一次执行开始时间点之后6秒再执行
     * @author Louis
     * @date 2019/6/23 18:06
     */
    @Scheduled(fixedRate = 3000)
    public void task02() {
        System.out.println("[" + Thread.currentThread().getName() + "]" + "-task02-每3秒执行一次-现在时间：" + dateFormat.format(new Date()));
    }

}
