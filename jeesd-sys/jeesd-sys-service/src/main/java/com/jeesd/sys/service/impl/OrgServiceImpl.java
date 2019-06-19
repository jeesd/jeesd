package com.jeesd.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeesd.sys.domain.OrgModel;
import com.jeesd.sys.mapper.OrgMapper;
import com.jeesd.sys.service.IOrgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织机构表 服务实现类
 * </p>
 *
 * @author song
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, OrgModel> implements IOrgService {

}
