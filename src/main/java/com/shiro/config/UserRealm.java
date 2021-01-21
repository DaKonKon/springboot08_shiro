package com.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

//自定义的realm extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        //用户名、密码 数据库取
        String name="root";
        String password = "123";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if(!userToken.getUsername().equals(name)){
            return null;//自动抛出异常 UnknownAccountException
        }
        //密码认证shiro做
        return new SimpleAuthenticationInfo("",password,"");
    }
}
