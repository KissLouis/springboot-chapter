package com.springboot.controller;

import com.springboot.entity.ScheduleJob;
import com.springboot.exception.ServiceException;
import com.springboot.service.JobService;
import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Louis
 * @title: JobController
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 21:34
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * @param []
     * @return com.springboot.util.result.Result
     * @description: //TODO 查看全部任务
     * @author Louis
     * @date 2019/6/25 20:46
     */
    @GetMapping
    public Result getAllJob() {
        return new Result(ResultCode.SUCCESS, jobService.getAllJob());
    }

    /**
     * @param [jobId]
     * @return com.springboot.util.result.Result
     * @description: //TODO 查询任务
     * @author Louis
     * @date 2019/6/25 20:46
     */
    @GetMapping("/{id}")
    public Result getJob(@PathVariable("id") Long jobId) throws ServiceException {
        return new Result(ResultCode.SUCCESS, jobService.select(jobId));
    }

    /**
     * @param [jobId, newScheduleJob]
     * @return com.springboot.util.result.Result
     * @description: //TODO 更新任务
     * @author Louis
     * @date 2019/6/25 20:46
     */
    @PutMapping("/update/{id}")
    public Result updateJob(@PathVariable("id") Long jobId, @RequestBody ScheduleJob newScheduleJob) throws ServiceException {
        return new Result(ResultCode.SUCCESS, jobService.update(jobId, newScheduleJob));
    }

    /**
     * @param [jobId]
     * @return com.springboot.util.result.Result
     * @description: //TODO 删除任务
     * @author Louis
     * @date 2019/6/25 20:45
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteJob(@PathVariable("id") Long jobId) throws ServiceException {
        return new Result(ResultCode.SUCCESS, jobService.delete(jobId));
    }

    /**
     * @param [newScheduleJob]
     * @return com.springboot.util.result.Result
     * @description: //TODO 创建定时任务
     * @author Louis
     * @date 2019/6/25 20:45
     */
    @PostMapping("/save")
    public Result saveJob(@RequestBody ScheduleJob newScheduleJob) throws ServiceException {
        return new Result(ResultCode.SUCCESS, jobService.add(newScheduleJob));
    }


    /**
     * @param [jobId]
     * @return com.springboot.util.result.Result
     * @description: //TODO 执行任务
     * @author Louis
     * @date 2019/6/25 20:47
     */
    @GetMapping("/run/{id}")
    public Result runJob(@PathVariable("id") Long jobId) throws ServiceException {
        return new Result(ResultCode.SUCCESS, jobService.run(jobId));
    }


    /**
     * @param [jobId]
     * @return com.springboot.util.result.Result
     * @description: //TODO 暂停任务
     * @author Louis
     * @date 2019/6/25 20:45
     */
    @GetMapping("/pause/{id}")
    public Result pauseJob(@PathVariable("id") Long jobId) throws ServiceException {
        return new Result(ResultCode.SUCCESS, jobService.pause(jobId));
    }

    /**
     * @param [jobId]
     * @return com.springboot.util.result.Result
     * @description: //TODO 继续执行任务
     * @author Louis
     * @date 2019/6/25 20:45
     */
    @GetMapping("/resume/{id}")
    public Result resumeJob(@PathVariable("id") Long jobId) throws ServiceException {
        return new Result(ResultCode.SUCCESS, jobService.resume(jobId));
    }
}
