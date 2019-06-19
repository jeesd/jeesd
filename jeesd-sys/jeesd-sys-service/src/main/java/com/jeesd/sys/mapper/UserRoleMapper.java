package com.jeesd.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeesd.sys.domain.UserRoleModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author song
 */

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleModel> {

}
