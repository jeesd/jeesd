package com.jeesd.sys.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeesd.common.enums.CommonEnum;
import com.jeesd.common.utils.LayoutTreeUtil;
import com.jeesd.common.utils.Result;
import com.jeesd.sys.domain.DeptModel;
import com.jeesd.sys.dto.DeptTree;
import com.jeesd.sys.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dept")
@Api("用户部门模块")
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;

    @ApiOperation(value="获取部门树列表", notes="获取部门树列表")
    @GetMapping(value = "/tree")
    public Result getRoleTreeList() {
        List<DeptTree> treeList = deptService.getDeptTree();
        if(null == treeList || treeList.size() <= 0) {
            return new Result(null, "没有可用部门！");
        }
        Page page = new Page();
        page.setRecords(LayoutTreeUtil.buildByLoop(treeList, 0L));
        return new Result(page);
    }

    @ApiOperation(value="获取部门列表", notes="获取部门列表")
    @GetMapping(value = "/list")
    public Result getRoleList() {
        List<DeptModel> list = deptService.list().stream()
                .filter(d -> d.getStatus() == 0)
                .collect(Collectors.toList());
        return new Result(list);
    }

    @ApiOperation(value="通过id查询部门", notes="通过id查询部门")
    @GetMapping(value = "/{id}")
    public Result getDeptById(@PathVariable Long id) {
        return new Result(deptService.getById(id));
    }

    @ApiOperation(value="添加部门", notes="添加部门")
    @PostMapping(value = "/depts")
    public Result addDept(DeptModel deptModel,
                     HttpServletRequest request) {
        if(null == deptModel ) {
            return new Result(null, "部门参数不能空！");
        }
        deptModel.setOperator("");
        deptModel.setCreateTime(LocalDateTime.now());
        deptModel.setStatus(CommonEnum.STATE_0.getValue());
        return new Result(deptService.save(deptModel));
    }

    @ApiOperation(value="修改部门", notes="修改部门")
    @PutMapping(value = "/modify")
    public Result updateDept(DeptModel deptModel,
                        HttpServletRequest request) {
        if(null == deptModel ) {
            return new Result(null, "部门参数不能空！");
        }
        deptModel.setOperator("");
        return new Result(deptService.updateDeptById(deptModel));
    }

    @ApiOperation(value="删除部门", notes="删除部门")
    @DeleteMapping(value = "/{id}/del")
    public Result deleteDept(@PathVariable Long id) {

        return new Result(deptService.removeDeptById(id));
    }
}
