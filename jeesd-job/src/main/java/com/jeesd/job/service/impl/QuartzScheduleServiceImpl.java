package com.jeesd.job.service.impl;

import com.jeesd.job.domain.ScheduleJobModel;
import com.jeesd.job.enums.JobOperateEnum;
import com.jeesd.job.quartz.QuartzJobFactory;
import com.jeesd.job.service.QuartzScheduleService;
import com.jeesd.job.service.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class QuartzScheduleServiceImpl implements QuartzScheduleService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Override
    public void loadingTask() {

        List<ScheduleJobModel> scheduleJobModelList = scheduleJobService.list();
        if(null != scheduleJobModelList && scheduleJobModelList.size() > 0) {
            scheduleJobModelList.forEach(this::addJob);
        }

    }

    @Override
    public void addJob(ScheduleJobModel jobModel) {

        //创建调度任务,任务名，任务组，任务执行类
        JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                .withIdentity(jobModel.getJobName(), jobModel.getJobGroup())
                .build();
        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobModel.getJobTriggerName(), jobModel.getJobTriggerGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(jobModel.getJobCronExpression()))
                .startNow()
                .build();
        try {
            //调度任务
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
           log.error("添加调度任务异常：", e);
        }
    }

    @Override
    public void operateJob(Integer status, ScheduleJobModel jobModel) throws SchedulerException {

        JobKey jobKey = new JobKey(jobModel.getJobName());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if(null == jobDetail) {
            throw new RuntimeException("操作调度任务失败，任务不存在！");
        }
        if(status == JobOperateEnum.START.getValue()) {
            scheduler.resumeJob(jobKey);
        } else if(status == JobOperateEnum.PAUSE.getValue()) {
            scheduler.pauseJob(jobKey);
        } else if(status == JobOperateEnum.DELETE.getValue()) {
            scheduler.deleteJob(jobKey);
        }
    }

    @Override
    public void startAllJob() throws SchedulerException {
        scheduler.start();
    }

    @Override
    public void pauseAllJob() throws SchedulerException {
        scheduler.standby();
    }
}
