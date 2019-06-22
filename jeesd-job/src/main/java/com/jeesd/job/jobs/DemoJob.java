package com.jeesd.job.jobs;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Component("demoJob")
@Transactional
public class DemoJob implements BaseJob {

    @Override
    public void execute() {
        System.out.println("-------------------demoJob任务执行开始-------------------");
        System.out.println(LocalTime.now());
        System.out.println("-------------------demoJob任务执行结束-------------------");
    }
}
