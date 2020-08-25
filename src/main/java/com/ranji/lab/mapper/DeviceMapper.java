package com.ranji.lab.mapper;

import com.ranji.lab.dto.DeviceAndDeviceTypeNameDto;
import com.ranji.lab.dto.DeviceAndModelDto;
import com.ranji.lab.dto.DeviceDto;
import com.ranji.lab.entity.Device;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeviceMapper {

    //插入
    @Insert("insert into device (factime,device_model_id,uuid) values (#{factime},#{deviceModelId},#{uuid}")
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

    @Select("select d.*,dt.type_name from device d left join device_type dt on dt.id = d.type")
    List<DeviceAndDeviceTypeNameDto> findDeviceAndDeviceName();
}
