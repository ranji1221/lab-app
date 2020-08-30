package com.ranji.lab.mapper;

import com.ranji.lab.entity.Study;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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

    //模糊查询
    @Select("<script>" +
            "select * from study where " +
            "title like '%${like}%' or " +
            "information_source like '%${like}%' or " +
            "author like '%${like}%' or " +
            "content like '%${like}%'" +
            "</script>")
    List<Study> findLikeStudy(@Param("like") String like);
}
