package com.ranji.lab.service.impl;

import com.ranji.lab.entity.Role;
import com.ranji.lab.mapper.RoleMapper;
import com.ranji.lab.service.prototype.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
