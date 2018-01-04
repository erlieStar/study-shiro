package com.makenv.controller;

import com.makenv.entity.ResourceEntity;
import com.makenv.service.SysResoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResoController {

    @Autowired
    SysResoService sysResoService;

    @ModelAttribute("types")
    public ResourceEntity.ResourceType[] resourceTypes() {
        return ResourceEntity.ResourceType.values();
    }

    @RequiresPermissions("resource:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("resourceList", sysResoService.findAll());
        return "resource/index";
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{id}/create", method = RequestMethod.GET)
    public String create(@PathVariable long id, Model model) {
        ResourceEntity parent = sysResoService.findOne(id);
        model.addAttribute("parent", parent);
        ResourceEntity son = new ResourceEntity();
        son.setParentId(parent.getId());
        model.addAttribute("resource", son);
        model.addAttribute("op","新增子节点");
        return "resource/edit";
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{id}/create", method = RequestMethod.POST)
    public String create(ResourceEntity resource, Model model) {
        sysResoService.createResource(resource);
        return "redirect:/resource";
    }

    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        //返回一个key为resource的属性，这样在form表单中的commandName属性中就能引用这个对象的属性
        model.addAttribute("resource", sysResoService.findOne(id));
        model.addAttribute("op","修改");
        return "resource/edit";
    }

    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(ResourceEntity resource) {
        sysResoService.updateResource(resource);
        return "redirect:/resource";
    }


    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable long id) {
        sysResoService.deleteResource(id);
        return "redirect:/resource";
    }
}
