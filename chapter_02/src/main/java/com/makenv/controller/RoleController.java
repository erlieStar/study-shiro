package com.makenv.controller;

import com.makenv.common.annotation.SysLog;
import com.makenv.entity.RoleEntity;
import com.makenv.entity.vo.RoleVo;
import com.makenv.service.SysResoService;
import com.makenv.service.SysRoleResoService;
import com.makenv.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysRoleResoService sysRoleResoService;

    @Autowired
    SysResoService sysResoService;

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        Map<Long, Set<String>> permissionMap = new HashMap<Long, Set<String>>();
        List<RoleEntity> list = sysRoleService.findAll();
        for (RoleEntity role : list) {
            Set<String> tempSet = sysRoleResoService.findPermissions(role.getId());
            permissionMap.put(role.getId(), tempSet);
        }
        model.addAttribute("roleList", list);
        model.addAttribute("permissionMap", permissionMap);
        return "role/index";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("role", new RoleVo());
        model.addAttribute("op", "新增");
        model.addAttribute("resourceList", sysResoService.findAll());
        return "role/edit";
    }

    @SysLog("创建角色")
    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(RoleVo roleVo) {
        sysRoleResoService.createRoleVo(roleVo);
        return "redirect:/role";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        RoleEntity role = sysRoleService.findOne(id);
        List<Long> list = sysRoleResoService.findResourceIds(id);
        RoleVo roleVo = new RoleVo(role);
        roleVo.setResourceIdList(list);
        model.addAttribute("op", "修改");
        model.addAttribute("role", roleVo);
        model.addAttribute("resourceList", sysResoService.findAll());
        return "role/edit";
    }

    @SysLog("更改角色")
    @RequiresPermissions("role:update")
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(RoleVo roleVo) {
        sysRoleResoService.updateRoleVo(roleVo);
        return "redirect:/role";
    }

    @SysLog("删除角色")
    @RequiresPermissions("role:delete")
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable long id) {
        sysRoleResoService.deleteRoleVo(id);
        return "redirect:/role";
    }

}
