package com.ranji.lab.security;

import com.ranji.lab.entity.Role;
import com.ranji.lab.entity.User;
import com.ranji.lab.service.prototype.IUserService;
import com.ranji.lab.util.JWTUtil;
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
     * 因为自定义了认证的实体，故而要重写此方法，否则报错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 用于为当前登录成功的用户授予权限和角色
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //-- 1. 获取认证主体信息（即token,由于我们认证的时候保存的就是token字符串）
        String token = principalCollection.toString();
        //-- 2. 获取用户名
        String userName = JWTUtil.getUsername(token);
        //-- 3. 根据用户名获取该用户的角色或权限信息即可，这里我们先获取到用户的角色信息返回
        SimpleAuthorizationInfo info = null;
        List<Role> roles = null;
        if(userName != null) {
            info = new SimpleAuthorizationInfo();
            roles = userService.getRoles(userName);
        }
        //-- 暂时不拿权限判断权限，只拿角色来判断
        //info.addStringPermission("user:list");
        for (Role role : roles) {
            info.addRole(role.getCode());
        }
        return info;
    }

    /**
     * 通过Token进行认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
        //-- 1. 获取认证实体（其实就是token）
        String token = (String)authenticationToken.getCredentials();
        //-- 2. 通过token获取到用户名
        String userName = JWTUtil.getUsername(token);
        //-- 3. 进行校验
        if (userName == null || !JWTUtil.verifyToken(token, userName)) {
            throw new AuthenticationException("token verify failure");
        }
        //-- 4. 返回认证信息
        return new SimpleAuthenticationInfo(token,token,this.getClass().getName());
    }

    public void clearCachedAuthorizationInfo(String principal){
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal,getName());
        clearCachedAuthenticationInfo(principals);
    }
}
