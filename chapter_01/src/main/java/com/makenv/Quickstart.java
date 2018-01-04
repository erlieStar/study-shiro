package com.makenv;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;


/**
 * @Author : lilimin
 * @Description : 官方快速文档
 * @Date : Created in 17:17 2017/12/21
 */
public class Quickstart {

    private static Logger log = Logger.getLogger(Quickstart.class);
    public static void main(String[] args) {

        //获取SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //获取SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //获取主题，这个Subject是外部程序和Shiro交互的对象
        Subject currentUser = SecurityUtils.getSubject();

        //获取session，并赋值再取出来
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }

        //如果用户没有登陆
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");

            //记住用户的信息，如何使在web环境下回将内容写进cookie，以后就不用重复获取内容
            token.setRememberMe(true);
            try {
                //用户登陆
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                //账号错误异常
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                //密码错误异常
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                //账户锁定异常
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            catch (AuthenticationException ae) {
                //登陆过程中总的异常
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        //打印登陆人员的信息
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //判断当前用户是否有schwartz这个角色
        if (currentUser.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }

        //判断当前用户是否有lightsaber:weild这个权限
        if (currentUser.isPermitted("lightsaber:weild")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        //登出
        currentUser.logout();

        System.exit(0);
    }
}
