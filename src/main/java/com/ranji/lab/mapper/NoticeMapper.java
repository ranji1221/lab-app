package com.ranji.lab.mapper;

import com.ranji.lab.entity.News;
import com.ranji.lab.entity.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface NoticeMapper {
    @Insert("insert into notice (title,information_source,author,time,content) values (#{title},#{informationSource},#{author},#{time},#{content})")
    int insertNotice(Notice notice);
    @Update("update notice set title = #{title}, author = #{author} ,information_source = #{informationSource}, time = #{time},content = #{content} where id = #{id};")
    int updateNotice(Notice notice);
    @Select("select notice.* from notice order by time desc")
    List<Notice> findAll();
    @Select("select notice.* from notice where notice.id = #{id}")
    Notice findById(int id);
    @Select("select notice.* from notice order by time desc limit #{noticeId},2")
    List<News> findNoticeNextToNext(int noticeId);
    @Select("select count(*) from notice")
    int count();
}
