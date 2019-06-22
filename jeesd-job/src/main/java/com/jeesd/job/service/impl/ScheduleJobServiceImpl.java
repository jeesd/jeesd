package com.jeesd.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeesd.job.domain.ScheduleJobModel;
import com.jeesd.job.enums.JobOperateEnum;
import com.jeesd.job.mapper.ScheduleJobMapper;
import com.jeesd.job.service.QuartzScheduleService;
import com.jeesd.job.service.ScheduleJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJobModel> implements ScheduleJobService {

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;
    @Autowired
    private QuartzScheduleService quartzScheduleService;

    @Override
    public boolean add(ScheduleJobModel jobModel) {
        //添加数据库数据
        if(null == jobModel) {
            return false;
        }
        if(scheduleJobMapper.insert(jobModel) <= 0) {
            return false;
        }
        quartzScheduleService.addJob(jobModel);
        return true;
    }

    @Override
    public boolean start(Long id) {
        return jobOperate(id, JobOperateEnum.START.getValue(), "启动任务失败");
    }

    @Override
    public boolean pause(Long id) {
        return jobOperate(id, JobOperateEnum.PAUSE.getValue(), "暂停任务失败");
    }

    @Override
    public boolean delete(Long id) {
        return jobOperate(id, JobOperateEnum.DELETE.getValue(), "删除任务失败");
    }

    private boolean jobOperate(Long id, Integer status, String errorDesc) {
        ScheduleJobModel jobModel = scheduleJobMapper.selectById(id);
        if(null == jobModel) {
            return false;
        }
        if(status == JobOperateEnum.DELETE.getValue()) {
            //直接删除数据库
            if(scheduleJobMapper.deleteById(id) <= 0) {
               return false;
            }
        } else {
            jobModel.setStatus(status);
            if(scheduleJobMapper.updateById(jobModel) <= 0) {
                return false;
            }
        }
        try {
            quartzScheduleService.operateJob(status, jobModel);
        } catch (SchedulerException e) {
            throw new RuntimeException(errorDesc, e);
        }
        return true;
    }

    @Override
    public void startAllJob() {
        ScheduleJobModel jobModel = new ScheduleJobModel();
        jobModel.setStatus(JobOperateEnum.START.getValue());
        this.update(jobModel, new UpdateWrapper<>());
        //启动job
        try {
            quartzScheduleService.startAllJob();
        } catch (SchedulerException e) {
            throw new RuntimeException("批量启动任务失败", e);
        }
    }

    @Override
    public void pauseAllJob() {
        ScheduleJobModel jobModel = new ScheduleJobModel();
        jobModel.setStatus(JobOperateEnum.PAUSE.getValue());
        this.update(jobModel, new UpdateWrapper<>());
        //暂停job
        try {
            quartzScheduleService.pauseAllJob();
        } catch (SchedulerException e) {
            throw new RuntimeException("批量暂停任务失败", e);
        }
    }
}
