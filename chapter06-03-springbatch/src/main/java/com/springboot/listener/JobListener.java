package com.springboot.listener;

import javax.annotation.Resource;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobListener implements JobExecutionListener {

	@Resource
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	private long startTime;

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		startTime = System.currentTimeMillis();
		log.info("job before " + jobExecution.getJobParameters());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		log.info("JOB STATUS : {}", jobExecution.getStatus());
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("JOB FINISHED");
			threadPoolTaskExecutor.destroy();
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			log.info("JOB FAILED");
		}
		log.info("Job Cost Time : {}ms", (System.currentTimeMillis() - startTime));
	}

}
