package com.ranji.lab.mapper;

import com.ranji.lab.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * UserMapper类
 */
public interface UserMapper {
    @Insert("insert into t_user(name,password,enable) values(#{name},#{password},#{enable})")
    void save(User u);
    @Select("select * from t_user")
    List<User> findAll();
}
