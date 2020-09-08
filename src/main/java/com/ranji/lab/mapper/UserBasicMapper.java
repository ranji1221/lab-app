package com.ranji.lab.mapper;

import com.ranji.lab.dto.UserBasicDto;
import com.ranji.lab.entity.UserBasic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserBasicMapper {

    @Insert("insert into t_user_basic (user_id,sex,phone,e_mail,remark) values (#{userId},#{sex},#{phone},#{eMail},#{remark})")
    int insertUserBasic(UserBasic userBasic);

    @Select("SELECT r.name role_name, b.sex, b.phone, b.e_mail, b.remark FROM t_user_basic b LEFT JOIN t_user u ON u.id = b.user_id LEFT JOIN t_user_role ur ON ur.user_id = u.id LEFT JOIN t_role r ON r.id = ur.role_id WHERE b.user_id = #{userId}")
    UserBasicDto findByUserId(int userId);

    @Select("select * from t_user_basic where user_id = #{id}")
    UserBasicDto findBasicById(int id);

    @Update("update t_user_basic set sex = #{sex} , phone = #{phone} , e_mail = #{eMail} , remark = #{remark} where user_id = #{userId}")
    int updateUserBasic(UserBasic userBasic);
}
