package com.ranji.lab.mapper;

import com.ranji.lab.dto.UserDto;
import com.ranji.lab.entity.Role;
import com.ranji.lab.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * UserMapper类
 */
public interface UserMapper {
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("insert into t_user(name,password,enable) values (#{name},#{password},#{enable})")
    int save(User u);
    @Select("select tu.*,tr.`name` roleName from t_user tu join t_user_role tur on tu.id = tur.user_id join t_role tr on tur.role_id = tr.id")
    List<UserDto> findAll();
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


    /**
     * 给某用户分配角色
     * @param userID
     * @param roleID
     */
    @Insert("insert into t_user_role(user_id,role_id) values(#{userID},#{roleID})")
    void saveRole(@Param("userID") int userID, @Param("roleID") int roleID);


    /**
     * 给某用户分配多种角色
     * @param userID
     * @param rolesID
     */
    @Insert("<script>\n" +
            "insert into t_user_role(user_id,role_id) values\n" +
            "    <foreach collection=\"rolesID\" item=\"roleID\" separator=\",\">\n" +
            "    (#{userID}, #{roleID})\n" +
            "    </foreach>\n" +
            "</script>")
    void saveRoles(@Param("userID") int userID, @Param("rolesID") int[] rolesID);

    /**
     * 通过用户id查询用户的角色
     * @param userID
     * @return
     */
    @Select("select r.* \n" +
            "from t_user_role ur\n" +
            "left join t_role r\n" +
            "on r.id = ur.role_id\n" +
            "where ur.user_id=#{userID}")
    Role getRole(int userID);
    /**
     * 通过用户名查询用户的角色
     * @param userName
     * @return
     */
    @Select("select r.*\n" +
            "from t_user u\n" +
            "left join t_user_role ur\n" +
            "on u.id = ur.user_id\n" +
            "left join t_role r\n" +
            "on r.id = ur.role_id\n" +
            "where u.name=#{userName}")
    Role getRoleByName(String userName);

    /**
     * 通过用户名查询用户所有的角色
     * @param userName
     * @return
     */
    @Select("select r.*\n" +
            "from t_user u\n" +
            "right join t_user_role ur\n" +
            "on u.id = ur.user_id\n" +
            "left join t_role r\n" +
            "on r.id = ur.role_id\n" +
            "where u.name=#{userName}")
    List<Role> getRoles(String userName);

    /**
     * 删除用户的所有角色
     * @param userID
     * @param rolesID
     */
    @Delete("<script>\n" +
            "delete from t_user_role where user_id=#{userID} and role_id in \n" +
            "    <foreach collection=\"rolesID\" item=\"roleID\" open=\"(\" separator=\",\" close=\")\">\n" +
            "    #{roleID}\n" +
            "    </foreach>\n" +
            "</script>")
    void cancelRoles(@Param("userID") int userID, @Param("rolesID") int[] rolesID);

    @Update("<script>" +
            "UPDATE `t_user` set " +
            "<if test = 'password != null or password != \"\"'>" +
            "`password` = #{password}" +
            "</if>" +
            " where id = #{id}" +
            "</script>")
    void updateUser(User u);

}
