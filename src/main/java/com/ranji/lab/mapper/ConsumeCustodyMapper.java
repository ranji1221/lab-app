package com.ranji.lab.mapper;

import com.ranji.lab.dto.ConsumeCustodyDto;
import com.ranji.lab.entity.ConsumeCustody;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumeCustodyMapper {
    @Insert("insert into consume_custody (consume_id,recipient,date,count,status) values (#{consumeId},#{recipient},#{date},#{count},0)")
    int insertConsumeCustody(ConsumeCustody consumeCustody);
    @Update("update consume_custody set (consume_id=#{consumeId},recipient=#{recipient},date=#{date},count=#{count},status=#{status}) where id = #{id}")
    int updateConsumeCustody(ConsumeCustody consumeCustody);
    @Select("select cc.*,ci.unit_name,ci.name consumeName from consume_custody cc left join consume_inform ci on cc.consume_id = ci.id order by date")
    List<ConsumeCustody> findAll();
    @Select("select cc.*,ci.unit_name,ci.name consumeName from consume_custody cc left join consume_inform ci on cc.consume_id = ci.id where consume_custody.id = #{id}")
    ConsumeCustody findById(int id);

    @Select("select cc.*,ci.name as consume_name,ci.unit_name from consume_custody cc join consume_inform ci on ci.id = cc.consume_id where cc.id = #{id}")
    ConsumeCustodyDto findNameById(int id);

    @Select("select count(*) from consume_custody")
    int getCount();

    //模糊查询
    @Select("select cc.*,ci.unit_name,ci.name consumeName from consume_custody cc left join consume_inform ci on cc.consume_id = ci.id " +
            " where cc.status != 2 " +
            " and ci.name like '%${like}%' or " +
            " ci.brand like '%${like}%' " +
            " order by date")
    List<ConsumeCustody> likefindAll(String like);
}
