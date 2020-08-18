package com.ranji.lab.mapper;

import com.ranji.lab.entity.ConsumePurchase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumePurchaseMapper {
    @Insert("insert into consume_purchase (name,num,date,applicant) values (#{name},#{num},#{date},#{applicant})")
    int insertConsumePurchase(ConsumePurchase consumePurchase);
    @Update("update consume_purchase set (name=#{name},num=#{num},date=#{date},applicant=#{applicant}) where id = #{id}")
    int updateConsumePurchase(ConsumePurchase consumePurchase);
    @Select("select consume_purchase.* from consume_purchase order by date desc")
    List<ConsumePurchase> findAll();
    @Select("select consume_purchase.* from consume_purchase where id = #{id}")
    ConsumePurchase findById(int id);
}
