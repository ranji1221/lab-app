package com.ranji.lab.security;

import com.ranji.lab.entity.Role;
import com.ranji.lab.entity.User;
import com.ranji.lab.service.prototype.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  授权与认证配置
 *  Shiro三大核心组件：
 *  1. Subject：认证主体
 *      它包含两个信息：Principals 和 Credentials。
 *      Principals：身份。可以是用户名，邮件，手机号码等等，用来标识一个登录主体身份；
 *      Credentials：凭证。常见有密码，数字证书等等。
 *      通俗来说，就是需要认证的东西，最常见的就是用户名密码了。
 *      比如用户在登录的时候，Shiro 需要去进行身份认证，就需要 Subject 认证主体。
 *  2. SecurityManager：安全管理器
 *      这是 Shiro 架构的核心，它就像 Shiro 内部所有原件的保护伞一样。
 *      在项目中一般都会配置 SecurityManager，主要是在 Subject 认证主体上面下功夫。
 *  3. Realms：Realms 是一个数据域
 *      它是连接 Shiro 和具体应用的桥梁，当需要与安全数据交互的时候，
 *      比如用户账户、访问控制等，Shiro 就会从一个或多个 Realms 中去查找访问控制数据。
 *      我们一般会自己定制Realm。
 *  @author RanJi
 *  @version jdk8
 */
public class SystemRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;

    /**
     * 用于为当前登录成功的用户授予权限和角色
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //-- 编写授权代码
        //-- 以下的代码是测试代码，假设所有的用户都会有"user:list"的权限
        //-- 在实际的开发中，我们会自己写好user-role-permission模块，然后从数据库中查询，用户的权限
        //-- 并可以赋予用户权限
        //-- 由于下面保存的User，所以这里拿到的就是User,若下面只保存用户名，则这里拿到的就是字符串用户名
        User user = (User)principalCollection.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo info = null;
        List<Role> roles = null;
        if(user != null) {
            info = new SimpleAuthorizationInfo();
            roles = userService.getRoles(user.getName());
        }
        //-- 暂时不拿权限判断权限，只拿角色来判断
        //info.addStringPermission("user:list");
        for (Role role : roles) {
            info.addRole(role.getCode());
        }
        return info;
    }

    /**
     * 用于验证当前登录的用户，获取认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //-- 编写认证代码
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //-- 1. 根据验证单填写的名字从后台查找该用户
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",token.getUsername());
        List<User> users = userService.getUsers(params);
        User user = null;
        if(users!=null && users.size()>0) user = users.get(0);

        //-- 2. 返回认证材料信息
        AuthenticationInfo  authenInfo = null;
        if(user != null) {
            //-- 这里直接把用户保存起来，以便更好的获取到用户的信息，先前只保存用户的名字，主要看第一个参数
            //-- 其实用SecurityUtils.getSubject().getPrincipal()这个方法，返回的就是这里第一个参数的值，一般只保存用户名
            //-- 而在做前后端分离的需要返回User
            String str = getName();
            authenInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            //-- authenInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
        }
        return authenInfo;
    }

    public void clearCachedAuthorizationInfo(String principal){
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal,getName());
        clearCachedAuthenticationInfo(principals);
    }
}
