package com.ranji.lab.mapper;

import com.ranji.lab.dto.DeviceAndDeviceTypeNameDto;
import com.ranji.lab.dto.DeviceDto;
import com.ranji.lab.entity.Device;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DeviceMapper {
    @Insert("insert into device (device_name,brand,num,facid,factime,proid,supid,type) values (#{deviceName},#{brand},#{num},#{facid},#{factime},#{proid},#{supid},#{type})")
    int insertDevice(DeviceDto deviceDto);
    @Update("update device set device_name = #{deviceName},brand = #{brand},conid =#{conid},num=#{num},roomnames=#{roomnames},facid=#{facid},factime=#{factime},proid=#{proid},supid=#{supid} where id = #{id}")
    int updateDevice(Device device);
    @Select("select device.* from device")
    List<Device> findAll();
    @Select("select device.* from device where device.id = #{id}")
    Device findById(int id);
    @Select("select device.* from device where type = #{type}")
    List<DeviceDto> findAllDeviceByTypeId(int type);
    @Select("select d.*,dt.type_name from device d left join device_type dt on dt.id = d.type")
    List<DeviceAndDeviceTypeNameDto> findDeviceAndDeviceName();
}
