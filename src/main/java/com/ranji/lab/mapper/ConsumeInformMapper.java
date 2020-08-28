package com.ranji.lab.mapper;

import com.ranji.lab.dto.ConsumeInformAndConsumeTypeNameDto;
import com.ranji.lab.dto.ConsumeInformDto;
import com.ranji.lab.entity.ConsumeInform;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumeInformMapper {
    @Insert("insert into consume_inform (name,brand,num,facid,factime,proid,supid,type,unit_name) values (#{name},#{brand},#{num},#{facid},#{factime},#{proid},#{supid},#{type},#{unitName})")
    int insertConsumeInform(ConsumeInformDto consumeInformDto);
    @Update("<script>" +
            "update consume_inform set " +
            "<if test = 'name != null '> " +
            "name=#{name}," +
            "</if>" +
            "<if test = 'brand != null '> " +
            "brand=#{brand}," +
            "</if>" +
            "<if test = 'facid != null '> " +
            "facid=#{facid}," +
            "</if>" +
            "<if test = 'factime != null '> " +
            "factime=#{factime}," +
            "</if>" +
            "<if test = 'proid != null '> " +
            "proid=#{proid}," +
            "</if>" +
            "<if test = 'supid != null '> " +
            "supid=#{supid}," +
            "</if>" +
            "<if test = 'type != null '> " +
            "type=#{type}," +
            "</if>" +
            "<if test = 'unitName != null '> " +
            "unit_name=#{unitName}," +
            "</if>" +
            "<if test = 'num != null '> " +
            "num=#{num}" +
            "</if>" +
            " where id =#{id}" +
            "</script>")
    int updateConsumeInform(ConsumeInform consumeInform);

    @Select("select consume_inform.* from consume_inform")
    List<ConsumeInform> findAll();

    //按照id查询耗材信息
    @Select("select consume_inform.* from consume_inform where consume_inform.id = #{id}")
    ConsumeInform findById(int id);

    //按照类型id查询耗材
    @Select("select consume_inform.* from consume_inform where type = #{type}")
    List<ConsumeInformDto> findAllConsumeInformByTypeId(int type);

    @Select("select ci.*,ct.type_name from consume_inform ci left join consume_type ct on ct.id = ci.type")
    List<ConsumeInformAndConsumeTypeNameDto> findConsumeAndConsumeName();

    //按照预约id查询实验耗材
    @Select("select ci.* from consume_inform ci join project_consume pc on ci.id = pc.experiment_consume_id join arrange a on a.id = pc.arrange_project_id where a.project_id = #{arrangeProjectId}")
    List<ConsumeInform> arrangeProjectIdFindconsumeInform(int arrangeProjectId);
}
