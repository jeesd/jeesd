package com.jeesd.job.controller;

import com.jeesd.common.utils.Result;
import com.jeesd.job.domain.ScheduleJobModel;
import com.jeesd.job.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/job")
@Api("调度任务模块")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @ApiOperation(value="添加任务", notes="添加任务")
    @PostMapping(value = "/jobs")
    public Result addJob(ScheduleJobModel scheduleJobModel,
                          HttpServletRequest request) {
        if(null == scheduleJobModel ) {
            return new Result(null, "任务参数不能空！");
        }
        scheduleJobModel.setOperator("");
        return new Result(scheduleJobService.add(scheduleJobModel));
    }

    @ApiOperation(value="启动任务", notes="启动任务")
    @GetMapping("/start/{id}")
    public Result start(@PathVariable("id") Long id) {
        return new Result(scheduleJobService.start(id), "启动任务成功");
    }

    @ApiOperation(value="暂停任务", notes="暂停任务")
    @GetMapping("/pause/{id}")
    public Result pause(@PathVariable("id") Long id) {
        return new Result(scheduleJobService.pause(id), "暂停任务成功");
    }

    @ApiOperation(value="删除任务", notes="删除任务")
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return new Result(scheduleJobService.delete(id), "删除任务成功");
    }

    @ApiOperation(value="批量启动任务", notes="批量启动任务")
    @GetMapping("/start/jobs")
    public Result startAllJob() {
        scheduleJobService.startAllJob();
        return new Result(null, "批量启动成功");
    }

    @ApiOperation(value="批量暂停任务", notes="批量暂停任务")
    @GetMapping("/pause/jobs")
    public Result pauseAllJob() {
        scheduleJobService.pauseAllJob();
        return new Result(null, "批量暂停成功");
    }


}
