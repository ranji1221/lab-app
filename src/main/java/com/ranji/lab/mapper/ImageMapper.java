package com.ranji.lab.mapper;

import com.ranji.lab.entity.Images;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ImageMapper {

    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty = "id")
    @Insert("insert into images (img_name,img_addr,img_description) values (#{imgName},#{imgAddr},#{imgDescription});")
    int insertImage(Images images);
    @Select("select MAX(id) from images;")
    int latestImageData();
    @Update("update images set img_name=#{imgName},img_addr=#{imgAddr},img_description=#{imgDescription} where id = #{id}")
    int updateImage(Images images);
    @Select("select * from images where id = #{id}")
    Images findImagesById(int id);
}
