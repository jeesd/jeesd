package com.jeesd.job.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_schedule_job")
public class ScheduleJobModel implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     *  主键ID
     */
    private Long id;
    /**
     *  任务名称
     */
    private String jobName;
    /**
     *  任务组
     */
    private String jobGroup;
    /**
     *  服务名称
     */
    private String jobBeanName;
    /**
     *  方法名称
     */
    private String jobMethodName;
    /**
     *  任务描述
     */
    private String jobDescription;
    /**
     * corn表达式
     */
    private String jobCronExpression;
    /**
     *  '状态 1.启动 2.暂停
     */
    private Integer status;
    /**
     *  任务类型 0.周期性 1.一次性
     */
    private Integer type;
    /**
     *  是否删除 0.否 1.是
     */
    private Integer deleteFlag;
    /**
     *  触发器名称
     */
    private String jobTriggerName;
    /**
     *  触发器组
     */
    private String jobTriggerGroup;
    /**
     *  操作人
     */
    private String operator;
    /**
     *  创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
