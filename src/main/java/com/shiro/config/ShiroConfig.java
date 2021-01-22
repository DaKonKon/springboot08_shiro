package com.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
    //创建realm对象，需要自定义类
    @Bean(name="userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(Collections.singleton(userRealm));
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //添加shiro的内置过滤器
        /*
            anno:无需认证即可访问
            authc:必须认证才能访问
            user:必须拥有记住我功能才能访问
            perms:必须拥有对某个资源的权限才能访问
            role:必须拥有某个角色权限才能访问
         */

        Map<String,String> filterMap = new LinkedHashMap<>();
        //授权,正常情况下，没有授权会跳转到未授权的页面
        filterMap.put("/user/add","perms[user:add]");
        //拦截
//        filterMap.put("/user/add","authc");
        filterMap.put("/user/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录路由
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    //整合ShiroDialect：用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }


}
