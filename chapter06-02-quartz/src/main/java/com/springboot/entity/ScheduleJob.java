package com.springboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Louis
 * @title: ScheduleJob
 * @projectName springboot-chapter
 * @description: TODO
 * @date 2019/6/23 21:15
 */
@Data
public class ScheduleJob implements Serializable {
    private static final long serialVersionUID = 4393648388916651928L;
    private Long id;

    private String className;

    private String cronExpression;

    private String jobName;

    private String jobGroup;

    private String triggerName;

    private String triggerGroup;

    private Boolean pause;

    private Boolean enable;

    private String description;

    private Date createTime;

    private Date lastUpdateTime;

    public Boolean getPause() {
        return pause;
    }

    public void setPause(Boolean pause) {
        this.pause = pause;
    }
}
