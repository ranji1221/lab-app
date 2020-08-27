package com.ranji.lab.mapper;

import com.ranji.lab.dto.ProjectDeviceDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 实验项目mapper
 */
public interface ProjectDeviceMapper {
    //插入
    @Insert("insert into project_device (experiment_device_id,arrange_project_id,device_num,status) values (#{experimentDeviceId},#{arrangeProjectId},1,0)")
    int insertProjectDevice(ProjectDeviceDto projectDeviceDto);

    //按照预约项目id查询实验所用设备信息及总数<!--问题-->
    @Select("select pd.*,dm.device_name,count(*) deviceNum,dm.unit_name unitName from project_device pd LEFT JOIN device d on pd.experiment_device_id = d.id LEFT JOIN device_model dm on dm.id = d.device_model_id where pd.arrange_project_id = #{arrangeProjectId} GROUP BY dm.device_name ")
    List<ProjectDeviceDto> projectIdFindProjectDeviceNum(int arrangeProjectId);
    //修改信息
    @Update("update arrange_project_device = #{arrangeProjectDevice} set device_num = #{deviceNum}, status = #{status} where id = #{id}")
    int updProjectDevice(ProjectDeviceDto projectDeviceDto);

}
