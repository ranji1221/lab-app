package com.ranji.lab.mapper;

import com.ranji.lab.entity.Auth;
import com.ranji.lab.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName:    RoleMapper
 * Package:    com.ranji.lab.mapper
 * Description: 角色Dao接口
 * Datetime:    2020/8/31   5:41 下午
 * Author:   ranji
 */
public interface RoleMapper {
    @Insert("insert into t_role(code,name) values(#{code},#{name})")
    void save(Role role);
    @Select("select * from t_role where id=#{id}")
    Role find(int id);
    @Select("select * from t_role")
    List<Role> findAll();


    /**
     * 给角色分配权限
     * @param roleID
     * @param authsID
     */
    @Insert("<script>\n" +
            "insert into t_role_auth(role_id,auth_id) values\n" +
            "    <foreach collection=\"authsID\" item=\"authID\" separator=\",\">\n" +
            "    (#{roleID}, #{authID})\n" +
            "    </foreach>\n" +
            "</script>")
    void assignRights(@Param("roleID") int roleID, @Param("authsID") int[] authsID);

    /**
     * 根据权限Code获取到对应的权限的ID
     * @param authCodes
     * @return
     */
    @Select("<script>\n" +
            "select id from t_auth where code in \n" +
            "    <foreach collection=\"authCodes\" item=\"authCode\" open=\"(\" separator=\",\" close=\")\">\n" +
            "    #{authCode}\n" +
            "    </foreach>\n" +
            "</script>")
    int[] getRightIDS(@Param("authCodes") String[] authCodes);

    /**
     * 给某角色取消某些权限
     * @param roleID
     * @param authsID
     */
    @Delete("<script>\n" +
            "delete from t_role_auth where role_id=#{roleID} and auth_id in \n" +
            "    <foreach collection=\"authsID\" item=\"authID\" open=\"(\" separator=\",\" close=\")\">\n" +
            "    #{authID}\n" +
            "    </foreach>\n" +
            "</script>")
    void cancelRights(@Param("roleID") int roleID, @Param("authsID") int[] authsID);

    /**
     * 根据角色ID获取该角色的权限
     * @param roleID
     * @return
     */
    @Select("select a.* \n" +
            "from t_role_auth ra\n" +
            "left join t_auth a\n" +
            "on a.id = ra.auth_id\n" +
            "where ra.role_id=#{roleID}")
    List<Auth> findAuths(int roleID);
}