package com.jeesd.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeesd.job.domain.ScheduleJobModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 任务调度 Mapper 接口
 * </p>
 *
 * @author song
 */
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobModel> {

}
