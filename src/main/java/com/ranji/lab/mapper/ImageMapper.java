package com.ranji.lab.mapper;

import com.ranji.lab.entity.Images;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface ImageMapper {
    @Insert("insert into images (img_name,img_addr,img_description) values (#{imgName},#{imgAddr}#{imgDescription});")
    int insertImage(Images images);
    @Select("select MAX(id) from images;")
    int latestImageData();
}
