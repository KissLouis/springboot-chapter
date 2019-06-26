### Quartz实现动态定时任务 
**创建定时任务：localhost:8080/job/save** 
```json
  {
      "className":"com.springboot.job.TestJob",
      "cronExpression":"*/10 * * * * ?",
      "jobName":"TestJob",
      "jobGroup":"TEST_GROUP",
      "triggerName":"TEST_TRIGGER",
      "triggerGroup":"TEST_GROUP",
      "pause": "false",
      "enable": "true",
      "description":"Test Job for SpringBoot"
  }
 ```
**暂停任务：http://localhost:8080/job/pause/1**

**继续执行任务：http://localhost:8080/job/resume/1**

**删除任务：http://localhost:8080/job/delete/1**

**更新任务：http://localhost:8080/job/update/1**
```json
  {
      "id":1,
      "className":"com.example.quartz.job.TestJob",
      "cronExpression":"*/5 * * * * ?",
      "jobName":"testJob",
      "jobGroup":"TEST_GROUP",
      "triggerName":"TEST_TRIGGER",
      "triggerGroup":"TEST_GROUP",
      "pause": "false",
      "enable": "true",
      "description":"test Job for SpringBoot"
  }
 ```
