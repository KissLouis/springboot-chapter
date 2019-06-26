package com.springboot.dao;

import com.springboot.entity.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Louis
 * @title: JobDao
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 21:17
 */
@Mapper
public interface JobDao {
    ScheduleJob select(@Param("id") long id);

    Integer update(ScheduleJob scheduleJob);

    Integer insert(ScheduleJob scheduleJob);

    Integer delete(Long productId);

    List<ScheduleJob> getAllJob();

    List<ScheduleJob> getAllEnableJob();
}
