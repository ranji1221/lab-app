package com.ranji.lab.mapper;

import com.ranji.lab.entity.Auth;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName:    AuthMapper
 * Package:    com.ranji.lab.mapper
 * Description:  权限Mapper
 * Datetime:    2020/9/1   3:15 下午
 * Author:   ranji
 */
public interface AuthMapper {
    @Insert("insert into t_auth(type,name,code) values(#{type},#{name},#{code})")
    void save(Auth auth);
    @Select("select * from t_auth")
    List<Auth> findAll();

    /**
     * 查询所有的权限分类
     * @return
     */
    @Select("select type from t_auth group by type")
    List<String> findAuthTypes();

    /**
     * 查询某个分类下的所有权限
     * @param type
     * @return
     */
    @Select("select * from t_auth where type=#{type}")
    List<Auth> findAuths(@Param("type") String type);
 }