package com.ranji.lab.mapper;

import com.ranji.lab.dto.DeviceTypeDto;
import com.ranji.lab.entity.DeviceType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DeviceTypeMapper {
    @Insert("insert into device_type (type_name) values (#{typeName})")
    int insertDeviceType(DeviceTypeDto deviceTypeDto);
    @Update("update device_type set (type_name = #{typeName}) where id = #{id}")
    int updateDeviceType(DeviceType deviceType);
    @Select("select * from device_type")
    List<DeviceType> allDeviceType();

}
