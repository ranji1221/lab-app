package com.ranji.lab.mapper;

import com.ranji.lab.entity.Monitaring;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MonitaringMapper {
    @Insert("insert into monitaring (laboratory_name,faculty,experiment_name,experiment_time,time,responsibility,status) values (#{laboratoryName},#{faculty},#{experimentName},#{experimentTime},#{time},#{responsibility},#{status})")
    int insertMonitaring(Monitaring monitaring);
    @Update("update monitaring set laboratory_name = #{laboratoryName} , faculty = #{faculty} , experiment_name=#{experimentName} , experiment_time = #{experimentTime},time = #{time},responsibility=#{responsibility},status=#{status} where id = #{id}")
    int updateMonitaring(Monitaring monitaring);
    @Select("select * from monitaring order by experiment_time desc")
    List<Monitaring> findAll();
    @Select("select * from monitaring where id = #{id}")
    Monitaring findById(int id);
}
