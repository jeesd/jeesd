package com.jeesd.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeesd.sys.domain.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author song
 */

@Mapper
public interface UserMapper extends BaseMapper<UserModel> {

    /**
     * 分页查询所有用户
     * @param page
     * @param user
     * @return
     */
    IPage<List<UserModel>> getUsersPage(Page page, @Param("user") UserModel user);
}
