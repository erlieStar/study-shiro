package com.makenv.controller;

import com.makenv.entity.ResourceEntity;
import com.makenv.entity.UserEntity;
import com.makenv.service.SysResoService;
import com.makenv.service.SysRoleService;
import com.makenv.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;


@Controller
public class LoginController {


    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysResoService sysResoService;

    /**
     * @Author : lilimin
     * @Description : 登陆页面处理
     * @Date : Created in 15:36 2017/12/22
     */
    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String username, @RequestParam(required = false) String password, Model model) {

        if (username == null || password == null) {
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String error = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "账号或密码不正确";
        } catch (IncorrectCredentialsException e) {
            error = "账号或密码不正确";
        } catch (LockedAccountException e) {
            error = "账号被锁定";
        } catch (AuthenticationException e) {
            error = "发生未知异常";
        }
        model.addAttribute("error", error);
        //发生错误
        if (error != null) {
            return "login";
        }
        UserEntity user = sysUserService.findByUsername(username);
        Set<String> permissions = sysUserService.findPermissions(user.getId());
        List<ResourceEntity> tempList = sysResoService.findMenu(permissions);
        model.addAttribute("menu", tempList);
        return "index";
    }

    /**
     * @Author : lilimin
     * @Description : 退出登录
     * @Date : Created in 13:46 2017/12/28
     */
    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }

    /**
     * @Author : lilimin
     * @Description : 登陆首页显示的页面
     * @Date : Created in 15:18 2017/12/26
     */
    @RequestMapping("/welcome")
    public String welcome(Model model) {
        return "welcome";
    }

}
