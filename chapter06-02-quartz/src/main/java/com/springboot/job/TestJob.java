package com.springboot.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Louis
 * @title: TestJob
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 21:38
 */
@Slf4j
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Test job is executing...");
    }
}
