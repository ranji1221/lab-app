package com.ranji.lab.mapper;

import com.ranji.lab.dto.DeviceTypeDto;
import com.ranji.lab.entity.DeviceModel;
import com.ranji.lab.entity.DeviceType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DeviceModelMapper {
    //插入
    @Insert("insert into device_model (device_name) values (#{deviceName})")
    int insertDeviceModel(DeviceModel deviceModel);
    //修改
    @Update("update device_model set (device_name = #{deviceName}) where id = #{id}")
    int updateDeviceModel(DeviceModel deviceModel);
    //查询全部
    @Select("select * from device_model")
    List<DeviceModel> allDeviceModel();
    //按照类型查询
    @Select("select * from device_model dm where dm.type = #{type} ")
    List<DeviceModel> allDeviceModelByType(int type);
}
