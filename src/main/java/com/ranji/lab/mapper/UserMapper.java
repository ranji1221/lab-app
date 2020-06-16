package com.ranji.lab.mapper;

import com.ranji.lab.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

/**
 * UserMapper类
 */
public interface UserMapper {
    @Insert("insert into t_user(name,password,enable) values(#{name},#{password},#{enable})")
    void save(User u);
    @Select("select * from t_user")
    List<User> findAll();
    @Select({
            "<script>",
            "select * from t_user",
            "where 1=1",
            "<when test='name != null'>",
            " and name like concat(concat('%', #{name, jdbcType=VARCHAR}),'%') ",
            "</when>",
            "<when test='password != null'>",
            " and password like concat(concat('%', #{password, jdbcType=VARCHAR}),'%') ",
            "</when>",
            "<when test='enable != null'>",
            " and enable = #{enable}",
            "</when>",
            "</script>"
    })
    List<User> find(Map<String,Object> params);
}
