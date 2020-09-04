package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Role;
import com.ranji.lab.entity.User;
import com.ranji.lab.mapper.UserMapper;
import com.ranji.lab.service.prototype.IUserService;
import org.apache.ibatis.annotations.Options;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /*@CacheEvict(value = "users", allEntries = true)*/
    public int save(User user) {
        userMapper.save(user);
        return user.getId();
    }

    /**
     * 查询的数据，可以利用@Cacheable进行缓存
     * @return
     */
    @Override
    /*@Cacheable(value = "users")*/
    public Map<Object,Object> getAllUsers(int pageNum , int pageSize) {
        PageHelper.startPage(pageNum , pageSize);
        List<User> all = userMapper.findAll();

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty()){
            allMap.put("data", all);
            allMap.put("total",total);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());

        return allMap;
    }

    /**
     * 根据不定参数条件查询用户数据
     * @param params
     * @return
     */
    @Override
    /*@Cacheable(value = "users")*/
    public List<User> getUsers(Map<String, Object> params) {
        return userMapper.find(params);
    }






    @Override
    public void assignRole(int userID, int roleID) {
        userMapper.saveRole(userID,roleID);
    }

    @Override
    public Role getRole(int userID) {
        return userMapper.getRole(userID);
    }

    @Override
    public Role getRole(String userName) {
        return userMapper.getRoleByName(userName);
    }

    @Override
    public void assignRoles(int userID, int[] rolesID) {
        userMapper.saveRoles(userID,rolesID);
    }

    @Override
    public List<Role> getRoles(String userName) {
        return userMapper.getRoles(userName);
    }

    @Override
    public void cancelRoles(int userID, int[] rolesID) {
        userMapper.cancelRoles(userID,rolesID);
    }

    @Override
    public void updateUser(User u) {
        userMapper.updateUser(u);
    }

}
