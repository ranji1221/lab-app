package com.ranji.lab.mapper;

import com.ranji.lab.entity.ConsumePurchase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumePurchaseMapper {
    @Insert("insert into consume_purchase (consume_id,num,date,applicant,status) values (#{consume_id},#{num},#{date},#{applicant},#{status})")
    int insertConsumePurchase(ConsumePurchase consumePurchase);
    @Update("update consume_purchase set (consume_id=#{consume_id},num=#{num},date=#{date},applicant=#{applicant},status = #{status}) where id = #{id}")
    int updateConsumePurchase(ConsumePurchase consumePurchase);
    @Select("select consume_purchase.* from consume_purchase order by date desc")
    List<ConsumePurchase> findAll();
    @Select("select consume_purchase.* from consume_purchase where id = #{id}")
    ConsumePurchase findById(int id);
}
