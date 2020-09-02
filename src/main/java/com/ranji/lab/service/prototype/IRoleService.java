package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Auth;
import com.ranji.lab.entity.Role;
import java.util.List;

/**
 * ClassName:    IRoleService
 * Package:    com.ranji.lab.service.prototype
 * Description: 角色业务服务类
 * Datetime:    2020/8/31   6:04 下午
 * Author:   ranji
 */
public interface IRoleService {
    void save(Role role);
    List<Role> findAll();
    Role find(int id);
    //--  授权方法，根据权限的ID，或者权限的Code名
    void assignAuths(int roleID,int[] authIDS);
    void assignAuths(int roleID,String[] authCodes);
    //--  取消权限的方法，根据权限的ID，或者权限的Code名
    void cancelAuths(int roleID,int[] authIDS);
    void cancelAuths(int roleID,String[] authCodes);

    //-- 获取某个角色的权限
    List<Auth> getAuths(int roleID);
}