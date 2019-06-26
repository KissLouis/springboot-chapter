package com.springboot.service.impl;

import com.springboot.dao.JobDao;
import com.springboot.entity.ScheduleJob;
import com.springboot.exception.ServiceException;
import com.springboot.service.JobService;
import com.springboot.util.schedule.ScheduleUtil;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Louis
 * @title: JobServiceImpl
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 21:22
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private Scheduler scheduler;


    @Override
    public List<ScheduleJob> getAllEnableJob() {
        return jobDao.getAllEnableJob();
    }

    @Override
    public ScheduleJob select(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = jobDao.select(jobId);
        if (scheduleJob == null) {
            throw new ServiceException("ScheduleJob:" + jobId + " not found");
        }
        return scheduleJob;
    }

    @Override
    public ScheduleJob update(Long jobId, ScheduleJob scheduleJob) throws ServiceException {
        if (jobDao.update(scheduleJob) <= 0) {
            throw new ServiceException("Update product:" + jobId + "failed");
        }

        ScheduleUtil.updateScheduleJob(scheduler, scheduleJob);

        return scheduleJob;
    }

    @Override
    public boolean add(ScheduleJob scheduleJob) throws ServiceException {
        Integer num = jobDao.insert(scheduleJob);
        if (num <= 0) {
            throw new ServiceException("Add product failed");
        }

        ScheduleUtil.createScheduleJob(scheduler, scheduleJob);

        return true;
    }

    @Override
    public boolean delete(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = select(jobId);

        Integer num = jobDao.delete(jobId);
        if (num <= 0) {
            throw new ServiceException("Delete product:" + jobId + "failed");
        }

        ScheduleUtil.deleteJob(scheduler, scheduleJob);

        return true;
    }

    @Override
    public List<ScheduleJob> getAllJob() {
        return jobDao.getAllJob();
    }

    @Override
    public boolean resume(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = updateScheduleJobStatus(jobId, false);
        ScheduleUtil.resumeJob(scheduler, scheduleJob);
        return true;
    }

    @Override
    public boolean pause(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = updateScheduleJobStatus(jobId, true);
        ScheduleUtil.pauseJob(scheduler, scheduleJob);
        return true;
    }

    @Override
    public boolean run(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = updateScheduleJobStatus(jobId, false);
        ScheduleUtil.run(scheduler, scheduleJob);
        return true;
    }

    private ScheduleJob updateScheduleJobStatus(Long jobId, Boolean isPause) throws ServiceException {
        ScheduleJob scheduleJob = select(jobId);
        scheduleJob.setPause(isPause);
        update(scheduleJob.getId(), scheduleJob);
        return scheduleJob;
    }

}
