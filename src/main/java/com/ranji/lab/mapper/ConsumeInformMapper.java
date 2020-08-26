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
    @Update("update consume_inform set name=#{name},brand=#{brand},num=#{num},facid=#{facid},factime=#{factime},proid=#{proid},supid=#{supid},type=#{type},unit_name=#{unitName} where id =#{id}")
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
}
