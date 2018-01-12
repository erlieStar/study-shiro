package com.makenv.controller;

import com.makenv.common.annotation.SysLog;
import com.makenv.entity.OrganEntity;
import com.makenv.service.SysOrganService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/organization")
public class OrganController {

    @Autowired
    SysOrganService sysOrganService;

    @RequiresPermissions("organization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<OrganEntity> organList = sysOrganService.findAll();
        model.addAttribute("organList", organList);
        return "organ/index";
    }


    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        OrganEntity organ = sysOrganService.findOne(id);
        model.addAttribute("organ", organ);
        return "organ/update";
    }

    @SysLog("更改组织")
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(OrganEntity organEntity) {
        sysOrganService.updateOrgan(organEntity);
        return "redirect:/organization";
    }

    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{id}/create", method = RequestMethod.GET)
    public String create(@PathVariable int id, Model model) {
        OrganEntity parent = sysOrganService.findOne(id);
        model.addAttribute("parent", parent);
        OrganEntity child = new OrganEntity();
        child.setParentId(id);
        model.addAttribute("child", child);
        return "organ/create";
    }

    @SysLog("创建组织")
    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{id}/create", method = RequestMethod.POST)
    public String create(OrganEntity organEntity) {
        sysOrganService.createOrgan(organEntity);
        return "redirect:/organization";
    }

    @SysLog("删除组织")
    @RequiresPermissions("organization:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        sysOrganService.deleteOrgan(id);
        return "redirect:/organization";
    }

    /**
     * @Author : lilimin
     * @Description : 移动节点
     * @Date : Created in 15:37 2018/1/2
     */
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/move", method = RequestMethod.GET)
    public String move(@PathVariable int id, Model model) {
        sysOrganService.deleteOrgan(id);
        return "redirect:/organization";
    }

    @SysLog("移动节点")
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/move", method = RequestMethod.POST)
    public String move() {
        return "redirect:/organization";
    }
}