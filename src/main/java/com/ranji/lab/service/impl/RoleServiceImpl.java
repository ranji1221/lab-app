package com.ranji.lab.service.impl;

import com.ranji.lab.entity.Auth;
import com.ranji.lab.entity.Role;
import com.ranji.lab.mapper.RoleMapper;
import com.ranji.lab.service.prototype.IRoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName:    RoleServiceImpl
 * Package:    com.ranji.lab.service.impl
 * Description: 角色业务实现类
 * Datetime:    2020/8/31   6:08 下午
 * Author:   ranji
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public void save(Role role) {
        roleMapper.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Role find(int id) {
        return roleMapper.find(id);
    }

    @Override
    public void assignAuths(int roleID, int[] authIDS) {
        roleMapper.assignRights(roleID,authIDS);
    }

    @Override
    public void assignAuths(int roleID, String[] authCodes) {
        int[] authIDS = roleMapper.getRightIDS(authCodes);
        roleMapper.assignRights(roleID,authIDS);
    }

    /**
     * 根据角色ID，及权限的ID取消权限
     * @param roleID
     * @param authIDS
     */
    @Override
    public void cancelAuths(int roleID, int[] authIDS) {
        roleMapper.cancelRights(roleID,authIDS);
    }

    /**
     * 根据角色的ID，及权限的Code取消相应的权限
     * @param roleID
     * @param authCodes
     */
    @Override
    public void cancelAuths(int roleID, String[] authCodes) {
        int[] authIDS = roleMapper.getRightIDS(authCodes);
        roleMapper.cancelRights(roleID,authIDS);
    }

    /**
     * 获取某个角色的所有权限
     * @param roleID
     * @return
     */
    @Override
    public List<Auth> getAuths(int roleID) {
        return roleMapper.findAuths(roleID);
    }
}