package com.ranji.lab.mapper;

import com.ranji.lab.dto.UserBasicDto;
import com.ranji.lab.entity.UserBasic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserBasicMapper {

    @Insert("insert into t_user_basic (user_id,nickname,sex,image_id ,phone, e_mail , remark) values (#{userId},#{nickname},#{sex},#{imageId},#{phone},#{eMail},#{remark})")
    int insertUserBasic(UserBasic userBasic);

    @Select("SELECT r.name role_name, b.nickname, b.sex, b.phone, b.e_mail, b.remark, i.image_addr FROM t_user_basic b LEFT JOIN t_user_image i ON i.id = b.image_id LEFT JOIN t_user u ON u.id = b.user_id LEFT JOIN t_user_role ur ON ur.user_id = u.id LEFT JOIN t_role r ON r.id = ur.role_id WHERE b.user_id = #{userId}")
    UserBasicDto findByUserId(int userId);
}
