package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.UserDto;
import com.ranji.lab.entity.Role;
import com.ranji.lab.entity.User;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 用户业务接口类
 * @author RanJi
 */
public interface IUserService {
    int save(User user);
    Map<Object,Object> getAllUsers(int pageNum , int pageSize);
    List<User> getUsers(Map<String,Object> params);



    /**
     * 根据角色ID给某用户赋予某种角色
     * @param userID
     * @param roleID
     */
    void assignRole(int userID, int roleID);

    /**
     * 根据用户ID获取该用户的角色
     * @param userID
     * @return
     */
    Role getRole(int userID);

    /**
     * 根据用户登陆名获取该用户的角色
     * @param userName
     * @return
     */
    Role getRole(String userName);

    /**
     * 给某用户赋予多种角色
     * @param userID
     * @param rolesID
     */
    void assignRoles(int userID, int[] rolesID);

    /**
     * 根据用户登陆名获取该用户所拥有的所有角色
     * @param userName
     * @return
     */
    List<Role> getRoles(String userName);

    /**
     * 给某用户撤销角色
     * @param userID
     * @param rolesID
     */
    void cancelRoles(int userID, int[] rolesID);

    /**
     * 修改用户信息
     * @param u
     */
    void updateUser(User u);

    /**
     * 查询所有教师角色用户
     */
    List<UserDto> findAllTeachers();

    /**
     * 查询所有学生角色用户
     */
    List<UserDto> findAllStudents();

    /**
     * 通过用户名获取教师id
     */
    int findUserIdByUserName(String name);
}
