package com.springboot.listener;

import com.springboot.entity.ScheduleJob;
import com.springboot.exception.ServiceException;
import com.springboot.service.JobService;
import com.springboot.util.schedule.ScheduleUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Louis
 * @title: ApplicationListener
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 21:36
 */
@Component
@Slf4j
public class ApplicationListener implements CommandLineRunner {
    @Autowired
    private JobService jobService;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {

        // 应用启动之后执行所有可执行的的任务
        List<ScheduleJob> scheduleJobList = jobService.getAllEnableJob();
        for (ScheduleJob scheduleJob : scheduleJobList) {
            try {
                CronTrigger cronTrigger = ScheduleUtil.getCronTrigger(scheduler, scheduleJob);
                if (cronTrigger == null) {
                    ScheduleUtil.createScheduleJob(scheduler, scheduleJob);
                } else {
                    ScheduleUtil.updateScheduleJob(scheduler, scheduleJob);
                }
                log.info("Startup {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
