package com.ranji.lab.mapper;

import com.ranji.lab.entity.StudyImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface StudyImageMapper {
    /**
     * 插入教学科研图片
     * @param studyImage
     * @return
     */
    @Insert("insert into study_image (image_addr) values (#{imageAddr})")
    //加入@Options是为了获取插入时的主键值；返回值对象是当前教学科研图片id值
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertStudyImage(StudyImage studyImage);

    /**
     * 通过id获取教学科研图片
     * @param id
     * @return
     */
    @Select("select * from study_image where id = #{id}")
    StudyImage findStudyImageById(int id);
}
