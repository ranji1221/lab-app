package com.ranji.lab.mapper;

import com.ranji.lab.entity.ConsumeCustody;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumeCustodyMapper {
    @Insert("insert into consume_custody (consume_id,recipient,date,count,status) values (#{consume_id},#{recipient},#{date},#{count},#{status})")
    int insertConsumeCustody(ConsumeCustody consumeCustody);
    @Update("update consume_custody set (consume_id=#{consume_id},recipient=#{recipient},date=#{date},count=#{count},status=#{status}) where id = #{id}")
    int updateConsumeCustody(ConsumeCustody consumeCustody);
    @Select("select consume_custody.* from consume_custody order by date")
    List<ConsumeCustody> findAll();
    @Select("select consume_custody.* from consume_custody where consume_custody.id = #{id}")
    ConsumeCustody findById(int id);
}
