package com.ranji.lab.mapper;

import com.ranji.lab.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    /**
     * 插入用户角色
     * @param role
     */
    @Insert("insert into t_role(code,name) values(#{code},#{name})")
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty = "id")
    void save(Role role);

    /**
     * 通过id查询角色
     * @param id
     * @return
     */
    @Select("select * from t_role where id=#{id}")
    Role find(int id);

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from t_role")
    List<Role> findAll();

}
