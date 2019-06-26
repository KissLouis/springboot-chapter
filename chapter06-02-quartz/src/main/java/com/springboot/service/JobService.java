package com.springboot.service;

import com.springboot.entity.ScheduleJob;
import com.springboot.exception.ServiceException;

import java.util.List;

/**
 * @author Louis
 * @title: JobService
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 21:19
 */
public interface JobService {

    public List<ScheduleJob> getAllEnableJob();

    public ScheduleJob select(Long jobId) throws ServiceException;

    public ScheduleJob update(Long jobId, ScheduleJob scheduleJob) throws ServiceException;

    public boolean add(ScheduleJob scheduleJob) throws ServiceException;

    public boolean delete(Long jobId) throws ServiceException;

    public List<ScheduleJob> getAllJob();

    public boolean resume(Long jobId) throws ServiceException;

    public boolean pause(Long jobId) throws ServiceException;

    public boolean run(Long jobId) throws ServiceException;
}
