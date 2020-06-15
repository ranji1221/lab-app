package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.User;
import java.util.List;

/**
 * 用户业务接口类
 * @author RanJi
 */
public interface IUserService {
    void save(User user);
    List<User> getAllUsers();
}
