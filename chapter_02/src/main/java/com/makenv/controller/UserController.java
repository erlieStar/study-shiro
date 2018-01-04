package com.makenv.controller;

import com.makenv.entity.RoleEntity;
import com.makenv.entity.UserEntity;
import com.makenv.entity.vo.UserVo;
import com.makenv.service.SysRoleService;
import com.makenv.service.SysUserRoleService;
import com.makenv.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleService sysRoleService;


    /**
     * @Author : lilimin
     * @Description : 为了方便其他人用自定义标签，我这里写了2种方案，这个是用自定义标签显示，页面转到user/index1
     * @Date : Created in 19:58 2018/1/3
     */
    /*@RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<UserEntity> list = sysUserService.findAll();
        List<UserVo> userList = new ArrayList<UserVo>();
        for (UserEntity user : list) {
            List<Long> tempList = sysUserRoleService.findRoleIds(user.getId());
            UserVo userVo = new UserVo(user);
            userVo.setRoleIdList(tempList);
            userList.add(userVo);
        }
        model.addAttribute("userList", userList);
        return "user/index1";
    }*/


    /**
     * @Author : lilimin
     * @Description : 这个没有用自定义标签，页面转到user/index
     * @Date : Created in 20:09 2018/1/3
     */
    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<UserEntity> list = sysUserService.findAll();
        Map<Long, List<String>> roleMap = new HashMap<Long, List<String>>();
        for (UserEntity user : list) {
            List<RoleEntity> tempList = sysUserRoleService.findRoles(user.getId());
            List<String> nameList = new ArrayList<String>();
            for (RoleEntity role : tempList) {
                nameList.add(role.getDescription());
            }
            roleMap.put(user.getId(), nameList);
        }
        model.addAttribute("userList", list);
        model.addAttribute("roleMap", roleMap);
        return "user/index";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        UserVo userVo = new UserVo();
        model.addAttribute("user", userVo);
        model.addAttribute("op", "新增");
        model.addAttribute("roleList", sysRoleService.findAll());
        return "user/edit";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(UserVo userVo) {
        sysUserRoleService.createUserVo(userVo);
        return "user/edit";
    }


    @RequiresPermissions("user:update")
    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        UserEntity userEntity = sysUserService.findOne(id);
        UserVo userVo = new UserVo(userEntity);
        model.addAttribute("user", userVo);
        model.addAttribute("op", "修改");
        model.addAttribute("roleList", sysRoleService.findAll());
        return "user/edit";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(UserVo userVo) {
        sysUserRoleService.updateUserVo(userVo);
        return "user/edit";
    }


    @RequiresPermissions("user:delete")
    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable long id) {
        sysUserRoleService.deleteUserVo(id);
        return "redirect:/user";
    }

    /**
     * @Author : lilimin
     * @Description : 修改密码
     * @Date : Created in 9:51 2018/1/3
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "{id}/change", method = RequestMethod.GET)
    public String change(@PathVariable long id, Model model) {
        return "user/change";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "{id}/change", method = RequestMethod.POST)
    public String change(@PathVariable long id, String newPassword) {
        sysUserService.changePassword(id, newPassword);
        return "redirect:/user";
    }

}
