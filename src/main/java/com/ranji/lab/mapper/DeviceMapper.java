package com.ranji.lab.mapper;

import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DeviceMapper {
    @Insert("insert into device (device_name,brand,conid,num,roomnames,facid,factime,proid,supid) values (#{deviceName},#{brand},#{conid},#{num},#{roomnames},#{facid},#{factime},#{proid},#{supid})")
    int insertDevice(Device device);
    @Update("update device set device_name = #{deviceName},brand = #{brand},conid =#{conid},num=#{num},roomnames=#{roomnames},facid=#{facid},factime=#{factime},proid=#{proid},supid=#{supid} where id = #{id}")
    int updateDevice(Device device);
    @Select("select device.* from device")
    List<Device> findAll();
    @Select("select device.* from device where device.id = #{id}")
    Device findById(int id);
}
