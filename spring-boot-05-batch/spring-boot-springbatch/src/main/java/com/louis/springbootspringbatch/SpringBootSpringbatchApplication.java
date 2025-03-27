package com.louis.springbootspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSpringbatchApplication implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job importTradeJob;

    public SpringBootSpringbatchApplication(JobLauncher jobLauncher, Job importTradeJob) {
        this.jobLauncher = jobLauncher;
        this.importTradeJob = importTradeJob;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpringbatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(importTradeJob, new JobParameters());
    }
}
