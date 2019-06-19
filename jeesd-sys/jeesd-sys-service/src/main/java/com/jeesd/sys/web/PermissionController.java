package com.jeesd.sys.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeesd.common.enums.CommonEnum;
import com.jeesd.common.utils.LayoutTreeUtil;
import com.jeesd.common.utils.Result;
import com.jeesd.sys.domain.ResourceModel;
import com.jeesd.sys.dto.MenuTree;
import com.jeesd.sys.dto.PermissionTree;
import com.jeesd.sys.dto.RoleTreeDto;
import com.jeesd.sys.service.IRoleService;
import com.jeesd.sys.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/permission")
@Api("用户权限模块")
public class PermissionController extends BaseController {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value="获取权限列表", notes="获取权限列表")
    @GetMapping(value = "/list")
    public Result getPermissionList() {
        List<MenuTree> treeList = resourceService.list()
                .stream()
                .map(MenuTree::new)
                .collect(Collectors.toList());
        Page page = new Page();
        page.setRecords(LayoutTreeUtil.buildByLoop(treeList, 0L));
        return  new Result(page);
    }

    @ApiOperation(value="获取权限列表", notes="获取权限列表")
    @GetMapping(value = "/tree")
    public Result getPermissionTree() {
        List<PermissionTree> treeList = resourceService.list()
                .stream()
                .map(PermissionTree::new)
                .collect(Collectors.toList());
        return  new Result(LayoutTreeUtil.buildByLoop(treeList, 0L));
    }

    @ApiOperation(value="获取权限菜单", notes="获取权限菜单")
    @GetMapping(value = "/menu")
    public Result getMenuByUser(HttpServletRequest request) {

        List<RoleTreeDto> roles = roleService.getRoleByUser(getUsername(request));
        if(roles == null || roles.size() <= 0) {
            return  new Result(null, "用户没有菜单权限！");
        }
        List<MenuTree> treeList = resourceService.getMenusByRoles(roles)
                .stream()
                .filter(r -> r.getType() == 0)
                .map(MenuTree::new)
                .collect(Collectors.toList());

        return  new Result(LayoutTreeUtil.buildByLoop(treeList, 0L));
    }

    @ApiOperation(value="获取菜单列表", notes="获取菜单列表")
    @GetMapping(value = "/menu/list")
    public Result getMenuList(HttpServletRequest request) {
        List<ResourceModel> resList = resourceService.list().stream()
                .filter(r -> r.getType() == 0)
                .collect(Collectors.toList());

        return  new Result(resList);
    }

    @ApiOperation(value="获取角色权限树", notes="获取角色权限树")
    @GetMapping(value = "/tree/{roleId}")
    public Result getPermissionByRole(@PathVariable Long roleId) {
        List<MenuTree> treeList = resourceService.queryMenuByRoleId(roleId)
                .stream()
                .filter(r -> r.getType() == 0)
                .map(MenuTree::new)
                .collect(Collectors.toList());

        return  new Result(LayoutTreeUtil.buildByLoop(treeList, 0L));
    }

    @ApiOperation(value="通过id查询权限", notes="通过id查询权限")
    @GetMapping(value = "/{id}")
    public Result getPermissionById(@PathVariable Long id) {

        return  new Result(resourceService.getById(id));
    }

    @ApiOperation(value="添加权限", notes="添加权限")
    @PostMapping(value = "/permissions")
    public Result addPermission(ResourceModel resourceModel,
                           HttpServletRequest request) {
        if(null == resourceModel ) {
            return  new Result(null, "权限参数不能空！");
        }
        resourceModel.setCreateTime(LocalDateTime.now());
        resourceModel.setOperator("");
        resourceModel.setStatus(CommonEnum.STATE_0.getValue());
        return  new Result(resourceService.save(resourceModel));
    }

    @ApiOperation(value="修改权限", notes="修改权限")
    @PutMapping(value = "/modify")
    public Result updatePermission(ResourceModel resourceModel,
                              HttpServletRequest request) {
        if(null == resourceModel ) {
            return  new Result(null, "权限参数不能空！");
        }
        resourceModel.setOperator("");
        return  new Result(resourceService.updateById(resourceModel));
    }

    @ApiOperation(value="删除权限", notes="删除权限")
    @DeleteMapping(value = "/{id}/del")
    public Result deletePermission(@PathVariable Long id) {
        return  new Result(resourceService.removeById(id));
    }
}
