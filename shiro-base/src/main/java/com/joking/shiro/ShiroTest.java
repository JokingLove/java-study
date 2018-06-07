package com.joking.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ShiroTest {

    public static void main(String[] args) {
        // 1.新建一个ini 文件

        // 2.通过IniSecurityManagerFactory，获取一个SecurityManager
//        Ini ini = Ini.fromResourcePath("classpath:shiro.ini");
//        Environment environment = new DefaultEnvironment(ini);
//        SecurityManager securityManager = environment.getSecurityManager();

        SecurityManager securityManager = new DefaultSecurityManager();

        // 设置realm
        Realm iniRealm = new IniRealm("classpath:shiro.ini");
        ((DefaultSecurityManager) securityManager).setRealm(iniRealm);

        // 3、将securityManager 设置到SecurityUtils 中
        SecurityUtils.setSecurityManager(securityManager);

        // 4、获取Subject对象（get current executor User）
        Subject currentUser = SecurityUtils.getSubject();

        // 5、认证，Authentication
        AuthenticationToken token = new UsernamePasswordToken("joking", "1234567");
        currentUser.login(token);
        boolean authenticated = currentUser.isAuthenticated();
        System.out.println(authenticated);


        // 6、授权 Authorization
        System.out.println(currentUser.hasRole("admin"));
        System.out.println(currentUser.isPermitted("user:view1"));

        Set set = new LinkedHashSet();
        Map map = new HashMap<String, String>();
    }
}
