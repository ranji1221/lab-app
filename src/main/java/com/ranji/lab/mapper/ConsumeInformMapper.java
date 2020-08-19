package com.ranji.lab.mapper;

import com.ranji.lab.entity.ConsumeInform;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumeInformMapper {
    @Insert("insert into consume_inform (name,brand,num,facid,factime,proid,supid) values (#{name},#{brand},#{num},#{facid},#{factime},#{proid},#{supid})")
    int insertConsumeInform(ConsumeInform consumeInform);
    @Update("update consume_inform set (name=#{name},brand=#{brand},num=#{num},facid=#{facid},factime=#{factime},proid=#{proid},supid=#{supid}) where id =#{id}")
    int updateConsumeInform(ConsumeInform consumeInform);
    @Select("select consume_inform.* from consume_inform")
    List<ConsumeInform> findAll();
    @Select("select consume_inform.* from consume_inform where consume_inform.id = #{id}")
    ConsumeInform findById(int id);
}
