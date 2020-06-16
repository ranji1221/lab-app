package com.ranji.lab.service.prototype;

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
}
