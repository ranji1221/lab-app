package com.ranji.lab.mapper;

import com.ranji.lab.entity.Notice;
import com.ranji.lab.entity.Regime;
import org.apache.ibatis.annotations.Insert;
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
}
