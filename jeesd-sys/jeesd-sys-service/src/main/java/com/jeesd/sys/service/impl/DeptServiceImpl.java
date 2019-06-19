package com.jeesd.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeesd.sys.domain.DeptModel;
import com.jeesd.sys.dto.DeptTree;
import com.jeesd.sys.mapper.DeptMapper;
import com.jeesd.sys.service.IDeptService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author song
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptModel> implements IDeptService {

    @Override
    public boolean updateDeptById(DeptModel deptModel) {

        return this.updateById(deptModel);
    }

    @Override
    public boolean removeDeptById(Long id) {
       //级联删除子部门，暂时不处理
        return this.removeById(id);
    }

    @Override
    public List<DeptTree> getDeptTree() {

        List<DeptModel> deptList = this.list();
        if (null == deptList) {
            return null;
        }
       return deptList
                .stream()
                .filter(d -> d.getStatus() == 0)
                .map(DeptTree::new)
                .collect(Collectors.toList());
    }
}
