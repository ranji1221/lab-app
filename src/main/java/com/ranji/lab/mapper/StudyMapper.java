package com.ranji.lab.mapper;

import com.ranji.lab.entity.Study;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudyMapper {
    @Insert("insert into study (title,information_source,author,time,content) values (#{title},#{informationSource},#{author},#{time},#{content})")
    int insertStudy(Study study);

    @Update("update study set title = #{title}, author = #{author} ,information_source = #{informationSource}, time = #{time},content = #{content} where id = #{id};")
    int updateStudy(Study study);

    @Select("select study.* from study order by time desc")
    List<Study> findAll();

    @Select("select study.* from study where study.id = #{id}")
    Study findById(int id);

    @Select("select study.* from study order by time desc limit #{studyId},2")
    List<Study> findStudyNextToNext(int studyId);

    @Select("select count(*) from study")
    int count();
}
