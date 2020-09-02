package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Auth;
import com.ranji.lab.entity.Role;
import com.ranji.lab.entity.User;
import java.util.List;
import java.util.Map;

/**
 * 用户业务接口类
 * @author RanJi
 */
public interface IUserService {
    void save(User user);
    List<User> getAllUsers();
    List<User> getUsers(Map<String,Object> params);

    /**
     * 根据角色ID给某用户赋予某种角色
     * @param userID
     * @param roleID
     */
    void assignRole(int userID, int roleID);

    /**
     * 根据用户ID获取该用户的角色
     * @param userID
     * @return
     */
    Role getRole(int userID);

    /**
     * 根据用户登陆名获取该用户的角色
     * @param userName
     * @return
     */
    Role getRole(String userName);

    /**
     * 给某用户赋予多种角色
     * @param userID
     * @param rolesID
     */
    void assignRoles(int userID, int[] rolesID);

    /**
     * 根据用户登陆名获取该用户所拥有的所有角色
     * @param userName
     * @return
     */
    List<Role> getRoles(String userName);

    /**
     * 给某用户撤销角色
     * @param userID
     * @param rolesID
     */
    void cancelRoles(int userID, int[] rolesID);

    /**
     * 根据用户名获取该用户所有的权限Code
     * 仅返回权限Code信息，感觉更加实用
     * @param userName
     * @return
     */
    List<String> getAuthsCode(String userName);

    /**
     * 根据用户名获取该用户所有的权限
     * 返回的信息更加详细，有这个需要可以调这个方法
     * @param userName
     * @return
     */
    List<Auth> getAuths(String userName);
}
