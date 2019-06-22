package com.jeesd.job.service;

import com.jeesd.job.domain.ScheduleJobModel;
import org.quartz.SchedulerException;

public interface QuartzScheduleService {

    /**
     * 服务器启动加载任务
     */
    void loadingTask();

    /**
     * 新增定时任务
     * @param jobModel
     */
    void addJob(ScheduleJobModel jobModel);

    /**
     * 操作定时任务
     * @param status
     * @param jobModel
     * @throws SchedulerException
     */
    void operateJob(Integer status, ScheduleJobModel jobModel) throws SchedulerException;

    /**
     * 启动所有任务
     */
    void startAllJob() throws SchedulerException;

    /**
     * 暂停所有任务
     */
    void pauseAllJob() throws SchedulerException;
}
