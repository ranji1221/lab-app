package com.ranji.lab.mapper;

import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.entity.Arrange;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 预约mapper
 */
public interface ArrangeMapper {
    //新增预约信息
    @Insert("insert into arrange (laboratory_id,project_id,num,arrange_time,date,time_start,time_stop,responsibility,status) values (#{laboratoryId},#{projectId},#{num},#{arrangeTime},#{date},#{timeStart},#{timeStop},#{responsibility},0)")
    int insertArrange(Arrange arrange);

    //查询全部
    @Select("<script>" +
            "select a.*,l.*, ep.experiment_name project_name from arrange a LEFT JOIN laboratory l on a.laboratory_id = l.id LEFT JOIN experiment_project ep on a.project_id = ep.id where a.status != 2" +
            "<if test = 'status != null '> " +
            "and a.status = #{status}" +
            "</if>" +
            "order by date asc ,time_start asc,time_stop asc" +
            "</script>")
    List<ArrangeDto> findAllArrange(@Param("status")Integer status);

    //验证当前时间，该实验室是否已经预约
    @Select("select * from arrange where status = 0 and laboratory_id = #{laboratoryId} and date = #{date} and ( #{timeStart}>=time_start and #{timeStart}<=time_stop or #{timeStop}>=time_start and #{timeStop}<=time_stop ) ")
    List<ArrangeDto>yesOrNoArrange(Arrange arrange);

    //按照id查询
    @Select("select a.*,l.*, ep.experiment_name project_name from arrange a LEFT JOIN laboratory l on a.laboratory_id = l.id LEFT JOIN experiment_project ep on a.project_id = ep.id where id = #{id}")
    ArrangeDto idFindArrange(int id);
    //修改该项目信息
    @Update("update arrange set laboratory_id=#{laboratoryId},project_id=#{projectId},num=#{num},arrange_time=#{arrangeTime},date=#{date},time_start=#{timeStart},time_stop=#{timeStop},responsibility=#{responsibility},status=#{status} where id = #{id}")
    int updArrange(Arrange arrange);
}
