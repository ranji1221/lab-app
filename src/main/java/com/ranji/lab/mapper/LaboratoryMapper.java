package com.ranji.lab.mapper;

import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.entity.Laboratory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LaboratoryMapper {
    @Insert("insert into laboratory (laboratory_name,faculty,laboratory_responsibility) values (#{laboratoryName},#{faculty},#{laboratoryResponsibility})")
    int insertLaboratory(LaboratoryDto laboratoryDto);
    @Update("update laboratory set laboratory_name=#{laboratoryName},faculty=#{faculty},laboratory_responsibility=#{laboratoryResponsibility} where id = #{id}")
    int updateLaborytory(Laboratory laboratory);
    @Select("select * from laboratory")
    List<Laboratory> findAll();

    //按照时间段查询可使用的实验室
    @Select("SELECT * FROM laboratory l WHERE id NOT IN (SELECT laboratory_id FROM arrange WHERE STATUS=0 AND date=#{date} AND (#{timeStart}>=time_start AND #{timeStart}<=time_stop OR #{timeStop}>=time_start AND #{timeStop}<=time_stop OR #{timeStart}<=time_start AND #{timeStop}>=time_stop))")
    List<Laboratory> dateFindAll(String date,String timeStart,String timeStop);
}
