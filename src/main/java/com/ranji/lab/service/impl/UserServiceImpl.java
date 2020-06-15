package com.ranji.lab.service.impl;

import com.ranji.lab.entity.User;
import com.ranji.lab.mapper.UserMapper;
import com.ranji.lab.service.prototype.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 *  用户业务接口实现类
 *  Redis的key生成策略：
 *      前缀::后缀
 *  例如：
 *      我们这里@Cacheable(value = "users")在Redis中的生成的key：
 *      users::UserServiceImpl.getAllUsers[]
 *  这是因为我在RedisConfig中配置了key的生成格式导致的
 *  Redis数据库的常用命令：
 *      keys *  查看所有的key
 *      get  "key"   获取某个key的值
 *  @author RanJi
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 一旦改动了数据，就可以利用@CacheEvict清空redis缓存
     * @param user
     */
    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void save(User user) {
        userMapper.save(user);
    }

    /**
     * 查询的数据，可以利用@Cacheable进行缓存
     * @return
     */
    @Override
    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }
}
