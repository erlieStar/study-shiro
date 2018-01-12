package com.makenv.controller;

import com.makenv.entity.LogEntity;
import com.makenv.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    SysLogService sysLogService;

    @RequiresPermissions("log:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<LogEntity> logEntities = sysLogService.findAll();
        model.addAttribute("logList", logEntities);
        return "log/index";
    }
}
