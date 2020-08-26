package com.ranji.lab.mapper;

import com.ranji.lab.dto.DeviceAndDeviceTypeNameDto;
import com.ranji.lab.dto.DeviceAndModelDto;
import com.ranji.lab.dto.DeviceDto;
import com.ranji.lab.dto.DeviceIntelligentAnalyzeDto;
import com.ranji.lab.entity.Device;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeviceMapper {

    //插入
    @Insert("insert into device (factime,device_model_id,uuid) values (#{factime},#{deviceModelId},#{uuid})")
    int insertDevice(Device device);
    //修改
    @Update("update device set factime = #{factime},device_model_id = #{deviceModelId},uuid = #{uuid}  where id = #{id}")
    int updateDevice(Device device);
    //查询全部
    @Select("select device.* from device")
    List<Device> findAll();
    @Select("select * from device d left join device_model dm on dm.id=d.device_model_id")
    List<DeviceAndModelDto> findAllDeviceAndModel();
    //按照id查询
    @Select("select device.* from device where device.id = #{id}")
    Device findById(int id);
    //按照类型查询
    @Select("select device.* from device where type = #{type}")
    List<DeviceDto> findAllDeviceByTypeId(int type);

    @Select("select * from device d left join device_model dm on dm.id = d.device_model_id left join  device_type dt on dm.type=dt.type_name")
    List<DeviceAndDeviceTypeNameDto> findDeviceAndDeviceName();

    //智能分析
    @Select("SELECT d.id,dm.device_name,dm.brand,ld.status,count(*),l.laboratory_name FROM device d JOIN device_model dm ON d.device_model_id=dm.id JOIN laboratory_device ld ON ld.device_id=d.id JOIN laboratory l ON l.id=ld.laboratory_id GROUP BY ld.laboratory_id,ld.STATUS")
    List<DeviceIntelligentAnalyzeDto> findIntelligentAnalyze();
}
