package com.jeesd.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jeesd.sys.domain.DeptModel;
import com.jeesd.sys.dto.DeptTree;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author song
 */
public interface IDeptService extends IService<DeptModel> {

    /**
     * 修改部门
     * @param deptModel
     * @return
     */
    public boolean updateDeptById(DeptModel deptModel);

    /**
     * 删除部门
     * @param id
     * @return
     */
    public boolean removeDeptById(Long id);

    /**
     * 查询部门树
    * @return
     */
    public List<DeptTree> getDeptTree();

}
