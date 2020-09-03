package com.ranji.lab.mapper;

import com.ranji.lab.entity.NewsImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface NewsImageMapper {
    /**
     * 插入新闻图片
     * @param newsImage
     * @return
     */
    @Insert("insert into news_image (image_addr) values (#{imageAddr})")
    //加入@Options是为了获取插入时的主键值；返回值对象是当前新闻图片id值
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertNewsImage(NewsImage newsImage);

    /**
     * 通过id获取新闻图片
     * @param id
     * @return
     */
    @Select("select * from news_image where id = #{id}")
    NewsImage findNewsImageById(int id);


    @Select("select Max(id) from news_image")
    int findLatestNewsImageId();
}
