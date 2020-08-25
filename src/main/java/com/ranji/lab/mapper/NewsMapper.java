package com.ranji.lab.mapper;

import com.ranji.lab.entity.News;
import com.ranji.lab.entity.NewsImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * 新闻Mapper类
 */
public interface NewsMapper {
    @Insert("insert into t_news(title,content,create_time,last_time) values(#{title},#{content},#{createTime},#{lastTime})")
    void saveNews(News news);
    @Select("select * from t_news where id=#{id}")
    News findNews(int id);
    @Insert("insert into t_news_image(title,url) values(#{title},#{url})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void saveNewsImage(NewsImage newsImage);
    @Select("select * from t_news_image where id=#{id}")
    NewsImage findNewsImage(int id);
}
