package com.ranji.lab.mapper;

import com.ranji.lab.entity.Laboratory;
import com.ranji.lab.entity.LaboratoryImage;
import com.ranji.lab.entity.StudyImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface LaboratoryImageMapper {
    /**
     * 插入实验室图片
     * @param laboratoryImage
     * @return
     */
    @Insert("insert into laboratory_image (image_addr) values (#{imageAddr})")
    //加入@Options是为了获取插入时的主键值；返回值对象是当前教学科研图片id值
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertLaboratoryImage(LaboratoryImage laboratoryImage);

    /**
     * 通过id获取实验室id图片
     * @param id
     * @return
     */
    @Select("select * from laboratory_image where id = #{id}")
    Laboratory findlaboratoryImageById(int id);
}
