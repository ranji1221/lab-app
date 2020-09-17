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

    @Select("select cc.id,a.id arrangeProjectId,ep.experiment_name projectName,cc.recipient,cc.date,cc.`status` from arrange a join experiment_project ep on a.project_id = ep.id join consume_custody cc on a.id = cc.arrange_project_id")
    List<ConsumeCustody> findAll();

    //通过id查询她所用到的耗材
    @Select("select ci.id,ci.name consumeName,pc.consume_num count,ci.unit_name from project_consume pc join consume_inform ci on pc.experiment_consume_id = ci.id where pc.arrange_project_id = #{projectId}")
    List<ConsumeCustody> projectIdFindAll(int projectId);


    //按照状态查询保管领用
    @Select("select cc.id,a.id arrangeProjectId,ep.experiment_name projectName,cc.recipient,cc.date,cc.`status` from arrange a join experiment_project ep on a.project_id = ep.id join consume_custody cc on a.id = cc.arrange_project_id where cc.status = #{status} order by date")
    List<ConsumeCustody> statusFindAll(Integer status);

    //审核保管领用
    @Update("update consume_custody set status=4 where id = #{id}")
    int updateConsumeCustodyStatus(ConsumeCustodyDto consumeCustody);

    @Select("select ci.id,ep.experiment_name projectName,ci.name consumeName,pc.consume_num count,cc.date,cc.recipient,ci.unit_name unitName from consume_custody cc join arrange a on cc.arrange_project_id = a.id join project_consume pc on a.id = pc.arrange_project_id join consume_inform ci on pc.experiment_consume_id = ci.id join experiment_project ep on a.project_id = ep.id where consume_custody.id = #{id}")
    ConsumeCustody findById(int id);

    @Select("select cc.*,pc.consume_num count,ci.name as consumeName,ci.unit_name unitName from consume_custody cc join arrange a on cc.arrange_project_id = a.id join project_consume pc on pc.arrange_project_id= a.id join consume_inform ci on ci.id = pc.experiment_consume_id where cc.id = #{id} limit 1")
    ConsumeCustodyDto findNameById(int id);

    @Select("select count(*) from consume_custody")
    int getCount();

    //模糊查询
    @Select("select cc.id,a.id arrangeProjectId,ep.experiment_name projectName,cc.recipient,cc.date,cc.`status` from arrange a join experiment_project ep on a.project_id = ep.id join consume_custody cc on a.id = cc.arrange_project_id  " +
            " where ep.experiment_name  like '%${like}%' or " +
            " cc.recipient like '%${like}%' ")
    List<ConsumeCustody> likefindAll(String like);

}
