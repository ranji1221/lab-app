package com.ranji.lab.mapper;

import com.ranji.lab.dto.ConsumePurchaseDto;
import com.ranji.lab.entity.ConsumePurchase;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumePurchaseMapper {
    @Insert("insert into consume_purchase (consume_id,num,date,applicant,status) values (#{consumeId},#{num},#{date},#{applicant},#{status})")
    int insertConsumePurchase(ConsumePurchase consumePurchase);
    @Update("<script>" +
            "update consume_purchase set " +
            "<if test = 'num != null '>"+
            "consume_id = #{consumeId}" +
            "</if>" +
            "<if test = 'num != null '>"+
            ",num=#{num}" +
            "</if>" +
            "<if test = 'date != null '>"+
            ",date=#{date}" +
            "</if>" +
            "<if test = 'applicant != null '>"+
            ",applicant=#{applicant}" +
            "</if>" +
            "<if test = 'status != null '>"+
            ",status = #{status}" +
            "</if>" +
            "where id = #{id}" +
            "</script>")
    int updateConsumePurchase(ConsumePurchase consumePurchase);
    @Select("select cc.*,ci.name consumeName,ci.unit_name from consume_purchase cc left join consume_inform ci on cc.consume_id = ci.id order by date desc")
    List<ConsumePurchase> findAll();
    @Select("select cc.*,ci.name consumeName,ci.unit_name from consume_purchase cc left join consume_inform ci on cc.consume_id = ci.id where id = #{id}")
    ConsumePurchase findById(int id);
    @Select("select cp.*,ci.name as consume_name,ci.unit_name unitName from consume_purchase cp join consume_inform ci on ci.id = cp.consume_id where cp.id = #{id}")
    ConsumePurchaseDto findNameById(int id);

    @Select("select count(*) from consume_purchase")
    int getCount();
}
