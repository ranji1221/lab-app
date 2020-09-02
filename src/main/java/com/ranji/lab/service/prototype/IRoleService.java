package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Role;

import java.util.List;

public interface IRoleService {
    void save(Role role);
    List<Role> findAll();
    Role find(int id);
}
