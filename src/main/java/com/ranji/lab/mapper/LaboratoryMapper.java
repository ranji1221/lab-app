package com.ranji.lab.mapper;

import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.dto.LaboratoryStatusMonitoringDto;
import com.ranji.lab.dto.StatusMonitoringDto;
import com.ranji.lab.entity.Laboratory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LaboratoryMapper {
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty = "id")
    @Insert("insert into laboratory (laboratory_name,faculty,laboratory_responsibility,img_src) values (#{laboratoryName},#{faculty},#{laboratoryResponsibility},#{imgSrc})")
    int insertLaboratory(LaboratoryDto laboratoryDto);
    @Update("update laboratory set laboratory_name=#{laboratoryName},faculty=#{faculty},laboratory_responsibility=#{laboratoryResponsibility},img_src=#{imgSrc} where id = #{id}")
    int updateLaborytory(Laboratory laboratory);
    @Select("select * from laboratory")
    List<Laboratory> findAll();

    //按照时间段查询可使用的实验室
    @Select("SELECT * FROM laboratory l WHERE id NOT IN (SELECT laboratory_id FROM arrange WHERE STATUS=0 AND date=#{date} AND (#{timeStart}>=time_start AND #{timeStart}<=time_stop OR #{timeStop}>=time_start AND #{timeStop}<=time_stop OR #{timeStart}<=time_start AND #{timeStop}>=time_stop))")
    List<Laboratory> dateFindAll(String date,String timeStart,String timeStop);

    //实验状态监控
    @Select("SELECT a.id, a.date, a.time_start, a.time_stop, ep.experiment_name, a.responsibility, a. STATUS, l.faculty, l.laboratory_name, a.laboratory_id,l.img_src FROM arrange a LEFT JOIN experiment_project ep ON ep.id = a.project_id LEFT JOIN laboratory l ON l.id = a.laboratory_id WHERE a.laboratory_id = #{laboratoryId} ORDER BY a.date ASC, a.time_start ASC, a.time_stop ASC LIMIT 1")
    StatusMonitoringDto laboratoryStatusMonitoring(int laboratoryId);

    //模糊查询实验室
    @Select("select * from laboratory l " +
            " where 1 = 1 " +
            " and l.laboratory_name like '%${like}%' or " +
            " l.faculty like '%${like}%' ")
    List<Laboratory> likeFindAll(String like);
}
