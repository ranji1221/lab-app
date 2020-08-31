package com.ranji.lab.mapper;

import com.ranji.lab.dto.ExperimentProjectDto;
import com.ranji.lab.entity.ExperimentProject;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 实验项目mapper
 */
public interface ExperimentProjectMapper {
    //新增
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty = "id")
    @Insert("insert into experiment_project (experiment_name,experiment_target,experiment_content,experiment_process,status) values (#{experimentName},#{experimentTarget},#{experimentContent},#{experimentProcess},0)")
    int insertExperimentProject(ExperimentProject experimentProject);
    @Select("select Max(id) from experiment_project")
    int latestExperimentProjectData();

    //查询全部
    @Select("<script>" +
            "select * from experiment_project  where status != 2 " +
            "<if test = 'status != null '> " +
            "and status = #{status}" +
            "</if>" +
            "</script>")
    List<ExperimentProjectDto> findAllExperimentProject(Integer status);

    //按照id查询项目
    @Select("select * from experiment_project where id = #{id}")
    ExperimentProjectDto idFindExperimentProject(int id);
    //修改该项目信息
    @Update("<script>" +
            "update experiment_project set " +
            "<if test = 'experimentName != null '>" +
            "experiment_name = #{experimentName}," +
            "</if>" +
            "<if test = 'experimentTarget != null '>" +
            "experiment_target = #{experimentTarget} ," +
            "</if>" +
            "<if test = 'experimentContent != null '>" +
            "experiment_content = #{experimentContent}, " +
            "</if>" +
            "<if test = 'experimentProcess != null '>" +
            "experiment_process = #{experimentProcess}," +
            "</if>" +
            "<if test = 'status != null '>" +
            "status = #{status} " +
            "</if>" +
            "where id = #{id}" +
            "</script>")
    int updExperimentProject(ExperimentProject experimentProject);

    //模糊查询
    @Select("<script>" +
            "select * from experiment_project  where status != 2 " +
            "and experiment_name like '%${like}%' or " +
            "experiment_target like '%${like}%' or " +
            "experiment_content like '%${like}%' " +
            "</script>")
    List<ExperimentProjectDto> findLikeExperimentProject(@Param("like") String like);
}