package com.ranji.lab.mapper;

import com.ranji.lab.dto.ConsumeCustodyDto;
import com.ranji.lab.dto.ConsumeCustodyInsertDto;
import com.ranji.lab.entity.ConsumeCustody;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumeCustodyMapper {
    @Insert("insert into consume_custody (recipient,date,arrange_project_id,status) values (#{recipient},#{date},#{arrangeProjectId},0)")
    int insertConsumeCustody(ConsumeCustodyInsertDto consumeCustodyInsertDto);
    @Update("update consume_custody set recipient=#{recipient},date=#{date},arrange_project_id=#{arrangeProjectId},status=#{status} where id = #{id}")
    int updateConsumeCustody(ConsumeCustody consumeCustody);
    @Select("select cc.* from consume_custody cc order by date")
    List<ConsumeCustody> findAll();
    @Select("select cc.*,ci.unit_name,ci.name consumeName from consume_custody cc left join consume_inform ci on cc.consume_id = ci.id where consume_custody.id = #{id}")
    ConsumeCustody findById(int id);

    @Select("select cc.*,pc.consume_num count,ci.name as consumeName,ci.unit_name unitName from consume_custody cc join arrange a on cc.arrange_project_id = a.id join project_consume pc on pc.arrange_project_id= a.id join consume_inform ci on ci.id = pc.experiment_consume_id where cc.id = #{id} limit 1")
    ConsumeCustodyDto findNameById(int id);

    @Select("select count(*) from consume_custody")
    int getCount();

    //模糊查询
    @Select("select cc.*,ci.unit_name,ci.name consumeName from consume_custody cc join arrange a on cc.arrange_project_id = a.id join project_consume pc on pc.arrange_project_id= a.id join consume_inform ci on ci.id = pc.experiment_consume_id " +
            " where cc.status != 2 " +
            " and ci.name like '%${like}%' or " +
            " ci.brand like '%${like}%' " +
            " order by date")
    List<ConsumeCustody> likefindAll(String like);
}
