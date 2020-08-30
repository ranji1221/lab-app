package com.ranji.lab.mapper;

import com.ranji.lab.entity.News;
import com.ranji.lab.entity.Regime;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RegimeMapper {
    @Insert("insert into regime (title,information_source,author,time,content) values (#{title},#{informationSource},#{author},#{time},#{content})")
    int insertRegime(Regime regime);

    @Update("update regime set title = #{title}, author = #{author} ,information_source = #{informationSource}, time = #{time},content = #{content} where id = #{id};")
    int updateRegime(Regime regime);

    @Select("select regime.* from regime order by time desc")
    List<Regime> findAll();

    @Select("select regime.* from regime where regime.id = #{id}")
    Regime findById(int id);

    @Select("select regime.* from regime order by time desc limit #{regimeId},2")
    List<News> findRegimeNextToNext(int regimeId);

    @Select("select count(*) from regime")
    int count();

    //模糊查询
    @Select("<script>" +
            "select * from regime where " +
            "title like '%${like}%' or " +
            "information_source like '%${like}%' or " +
            "author like '%${like}%' or " +
            "content like '%${like}%'" +
            "</script>")
    List<Regime> findLikeRegime(@Param("like") String like);
}
