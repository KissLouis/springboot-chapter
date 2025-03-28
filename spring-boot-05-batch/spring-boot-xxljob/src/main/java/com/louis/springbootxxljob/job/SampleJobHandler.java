package com.louis.springbootxxljob.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class SampleJobHandler {

    /**
     * 简单定时任务
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        System.out.println("[XXL-JOB] 任务执行成功：" + System.currentTimeMillis());
    }

    /**
     * 分片任务示例
     */
    @XxlJob("shardingJobHandler")
    public void shardingJobHandler() throws Exception {
        // 获取分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        System.out.printf("[分片任务] 当前分片：%d/%d %n", shardIndex, shardTotal);
    }

    /**
     * 任务参数传递
     */
    @XxlJob("paramJobHandler")
    public void paramJobHandler() throws Exception {
        // 获取任务参数
        String jobParam = XxlJobHelper.getJobParam();
        System.out.println("接收参数：" + jobParam);
    }

}