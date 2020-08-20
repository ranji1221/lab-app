package com.ranji.lab.mapper;

import com.ranji.lab.dto.ExperimentProjectDto;
import com.ranji.lab.dto.ProjectDeviceDto;
import com.ranji.lab.entity.ExperimentProject;
import com.ranji.lab.entity.ProjectDevice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 实验项目mapper
 */
public interface ProjectDeviceMapper {
    //插入
    @Insert("insert into project_device (experiment_device_id,project_id,device_num,status) values (#{experimentDeviceId},#{projectId},#{deviceNum},0)")
    int insertProjectDevice(ProjectDeviceDto projectDeviceDto);
    //查询全部
    @Select("select pd.*,d.device_name from project_device pd LEFT JOIN device d on pd.experiment_device_id = d.id where pd.project_id = #{projectId} and status != 1")
    List<ProjectDeviceDto> projectIdFindAllProjectDevice(int projectId);
    //分类查询
    @Select("select pd.*,d.device_name from project_device pd LEFT JOIN device d on pd.experiment_device_id = d.id where pd.project_id = #{projectId} and pd.status = #{status}")
    List<ProjectDeviceDto> typeProjectIdFindAllProjectDevice(int projectId,int status);
    //修改信息
    @Update("update project_device set device_num = #{deviceNum}, status = #{status} where id = #{id}")
    int updProjectDevice(ProjectDeviceDto projectDeviceDto);

}
