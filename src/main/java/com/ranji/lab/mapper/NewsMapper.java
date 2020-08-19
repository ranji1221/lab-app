package com.ranji.lab.mapper;

import com.ranji.lab.entity.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 新闻Mapper
 * 1.插入新的新闻
 * 2.更新新闻
 * 3.按照时间远近距离排序(查询的第一条就是最近的新闻)查询所有新闻
 * 4.通过新闻id查询该条新闻
 */
public interface NewsMapper {
    @Insert("insert into news (title,information_source,author,time,content) values (#{title},#{informationSource},#{author},#{time},#{content})")
    int insertNews(News news);
    @Update("update news set title = #{title}, author = #{author} ,information_source = #{informationSource}, time = #{time},content = #{content} where id = #{id}")
    int updateNews(News news);
    @Select("select news.* from news order by time desc")
    List<News> findAll();
    @Select("select news.* from news where news.id = #{id}")
    News findById(int id);
    @Select("select news.* from news order by time desc limit #{newsId},2")
    List<News> findNewsNextToNext(int newsId);
}
