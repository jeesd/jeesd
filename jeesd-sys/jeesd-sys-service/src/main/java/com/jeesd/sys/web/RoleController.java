package com.jeesd.sys.web;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeesd.common.enums.CommonEnum;
import com.jeesd.common.utils.Result;
import com.jeesd.sys.domain.RoleModel;
import com.jeesd.sys.domain.RoleResourceModel;
import com.jeesd.sys.domain.UserRoleModel;
import com.jeesd.sys.service.IRoleResourceService;
import com.jeesd.sys.service.IRoleService;
import com.jeesd.sys.service.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
@Api("用户角色模块")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleResourceService roleResourceService;
    @Autowired
    private IUserRoleService userRoleService;

    @ApiOperation(value="获取角色列表", notes="获取角色列表")
    @GetMapping(value = "/list")
    public Result getRoleList(Page page, RoleModel roleModel) {

        return new Result (roleService.getRolesPage(page, roleModel));
    }

    @ApiOperation(value="获取全部角色", notes="获取全部角色")
    @GetMapping(value = "/all")
    public Result getRoleAll() {

        List<RoleModel> list = roleService.list()
                .stream()
                .filter(r -> r.getStatus() == 0)
                .collect(Collectors.toList());
        return new Result (list);
    }

    @ApiOperation(value="获取全部角色", notes="获取全部角色")
    @GetMapping(value = "/list/user")
    public Result getRoleListByUser(@RequestParam("userId") Long userId) {

        List<Long> list = userRoleService.list(Wrappers.<UserRoleModel>query()
                .lambda().eq(UserRoleModel::getUserId, userId))
                .stream()
                .map(UserRoleModel::getRoleId)
                .collect(Collectors.toList());
        return new Result (list);
    }

    @ApiOperation(value="通过id查询角色", notes="通过id查询角色")
    @GetMapping(value = "/{id}")
    public Result getRoleById(@PathVariable Long id) {
        return new Result (roleService.getById(id));
    }

    @ApiOperation(value="添加角色", notes="添加角色")
    @PostMapping(value = "/roles")
    public Result addRole(RoleModel roleModel,
                     HttpServletRequest request) {
        if(null == roleModel ) {
            return new Result (null, "角色参数不能空！");
        }
        roleModel.setOperator("");
        roleModel.setCreateTime(LocalDateTime.now());
        roleModel.setStatus(CommonEnum.STATE_0.getValue());
        return new Result (roleService.save(roleModel));
    }

    @ApiOperation(value="修改角色", notes="修改角色")
    @PutMapping(value = "/modify")
    public Result updateRole(RoleModel roleModel,
                        HttpServletRequest request) {
        if(null == roleModel ) {
            return new Result (null, "角色参数不能空！");
        }
        roleModel.setOperator("");
        return new Result (roleService.updateById(roleModel));
    }

    @ApiOperation(value="删除角色", notes="删除角色")
    @DeleteMapping(value = "/{id}/del")
    public Result deleteRole(@PathVariable Long id) {
        return new Result (roleService.removeById(id));
    }

    @ApiOperation(value="更新角色权限", notes="更新角色权限")
    @PutMapping(value = "/permissions")
    public Result updateRolePermission(@RequestParam("roleId") Long roleId,
                                  @RequestParam(value = "permissionIds[]", required = false) Long[] permissionIds) {
        return new Result (roleResourceService.saveRoleResource(roleId, permissionIds));
    }

    @ApiOperation(value="查询选中角色权限", notes="查询选中角色权限")
    @GetMapping(value = "/checked/permissions")
    public Result getRoleCheckedPermission(@RequestParam("roleId") Long roleId) {

        List<Long> permissions =  roleResourceService
                .list(Wrappers.<RoleResourceModel>query()
                .lambda().eq(RoleResourceModel::getRoleId, roleId))
                .stream()
                .map(RoleResourceModel::getResourceId)
                .collect(Collectors.toList());
        return new Result (permissions);
    }
}
