package com.jeesd.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jeesd.job.domain.ScheduleJobModel;

public interface ScheduleJobService  extends IService<ScheduleJobModel> {

    /**
     * 新增定时任务
     *
     * @param jobModel
     */
    boolean add(ScheduleJobModel jobModel);

    /**
     * 启动定时任务
     *
     * @param id 任务id
     */
    boolean start(Long id);

    /**
     * 暂停定时任务
     *
     * @param id 任务id
     */
    boolean pause(Long id);

    /**
     * 删除定时任务
     *
     * @param id 任务id
     */
    boolean delete(Long id);

    /**
     * 启动所有定时任务
     *
     */
    void startAllJob();

    /**
     * 暂停所有定时任务
     *
     */
    void pauseAllJob();
}
