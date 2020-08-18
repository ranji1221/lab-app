package com.ranji.lab.mapper;

import com.ranji.lab.entity.ConsumeCustody;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumeCustodyMapper {
    @Insert("insert into consume_custody (name,recipient,date,count,surplus_num,status) values (#{name},#{recipient},#{date},#{count},surplus_num+#{count},#{status})")
    int insertConsumeCustody(ConsumeCustody consumeCustody);
    @Update("update consume_custody set (name=#{name},recipient=#{recipient},date=#{date},count=#{count},surplus_num=surplus_num-#{count},status=#{status}) where id = #{id}")
    int updateConsumeCustody(ConsumeCustody consumeCustody);
    @Select("select consume_custody.* from consume_custody order by date")
    List<ConsumeCustody> findAll();
    @Select("select consume_custody.* from consume_custody where consume_custody.id = #{id}")
    ConsumeCustody findById(int id);
}
