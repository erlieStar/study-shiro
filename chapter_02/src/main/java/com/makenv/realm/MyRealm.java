package com.makenv.realm;

import com.makenv.dao.UserDao;
import com.makenv.entity.UserEntity;
import com.makenv.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm{

    @Autowired
    private SysUserService sysUserService;

    /**
     * @Author : lilimin
     * @Description : 授权，验证权限时调用
     * @Date : Created in 9:44 2017/12/22
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserEntity user = (UserEntity)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> set = sysUserService.findPermissions(user.getId());
        info.setStringPermissions(sysUserService.findPermissions(user.getId()));
        return info;
    }

    /**
     * @Author : lilimin
     * @Description : 认证，登陆时调用
     * @Date : Created in 9:45 2017/12/22
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        UserEntity user = sysUserService.findByUsername(token.getUsername());

        //账号不存在抛出异常
        if (user == null) {
            throw new UnknownAccountException();
        }

        //需要传入的4个参数为身份信息，凭据，盐，realm的名字
        //这个应该是将用户输入的密码和数据库中的盐再用算法进行一次加密，看和数据库中加密的密码是否一致
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }
}
