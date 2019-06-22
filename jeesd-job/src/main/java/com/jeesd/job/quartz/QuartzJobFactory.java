package com.jeesd.job.quartz;

import com.jeesd.common.utils.SpringUtil;
import com.jeesd.job.domain.ScheduleJobModel;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

public class QuartzJobFactory implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //获取调度任务数据
        ScheduleJobModel scheduleJobModel = (ScheduleJobModel) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");

        //获取对应的Bean
        Object object = SpringUtil.getBean(scheduleJobModel.getJobBeanName());
        try {
            //利用反射执行对应方法
            Method method = object.getClass().getMethod(scheduleJobModel.getJobMethodName());
            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
