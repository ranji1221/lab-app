package com.ranji.lab.mapper;

import com.ranji.lab.dto.ExperimentProjectDto;
import com.ranji.lab.entity.ExperimentProject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 预约mapper
 */
public interface arrangeMapper {
    @Insert("insert into arrange () values ()")
    int insertExperimentProject(ExperimentProject experimentProject);
    @Select("select Max(id) from experiment_project")
    int latestExperimentProjectData();

    //查询全部
    @Select("select * from experiment_project")
    List<ExperimentProjectDto> findAllExperimentProject();
    //过滤掉删除的
    @Select("select * from experiment_project where status != 2")
    List<ExperimentProjectDto> noDelExperimentProject();
    //分类查询
    @Select("select * from experiment_project where status = #{status}")
    List<ExperimentProjectDto> typeExperimentProject(int status);
    //按照id查询项目
    @Select("select * from experiment_project where id = #{id}")
    ExperimentProjectDto idFindExperimentProject(int id);
    //修改该项目信息
    @Update("update experiment_project set experiment_name = #{experimentName}, experiment_target = #{experimentTarget} ,experiment_content = #{experimentContent}, experiment_process = #{experimentProcess},status = #{status} where id = #{id}")
    int updExperimentProject(ExperimentProject experimentProject);
}
