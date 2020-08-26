package com.ranji.lab.mapper;

import com.ranji.lab.dto.ConsumeNormDto;
import com.ranji.lab.entity.ConsumeNorm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumeNormMapper {
    @Insert("insert into consume_norm (title,information_source,author,time,content) values (#{title},#{informationSource},#{author},#{time},#{content})")
    int insertConsumeNorm(ConsumeNormDto consumeNormDto);
    @Update("update consume_norm set title = #{title}, author = #{author} ,information_source = #{informationSource}, time = #{time},content = #{content} where id = #{id}")
    int updateConsumeNorm(ConsumeNorm consumeNorm);
    @Select("select consume_norm.* from consume_norm order by time desc")
    List<ConsumeNorm> findAll();
    @Select("select consume_norm.* from consume_norm where id = #{id}")
    ConsumeNorm findById(int id);

    @Select("select * from consume_norm order by time desc")
    List<ConsumeNorm> findAllConsumeNorm();
}
