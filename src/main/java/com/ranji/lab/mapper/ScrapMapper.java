package com.ranji.lab.mapper;

import com.ranji.lab.entity.Scrap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ScrapMapper {

    @Insert("insert into scrap (device_id,description,date,status) values (#{deviceId},#{description},#{date},0)")
    int insertScarp(Scrap scrap);
    //修改报废设备状态
    @Update("update device set status = 2 where id = #{deviceId}")
    int updateDeviceStatusToStartScrap(int deviceId);


    @Update("update scrap set device_id = #{deviceId},description = #{description},date=#{date} where id = #{deviceId}")
    int updateScarp(Scrap scrap);
    //修改报废设备状态
    @Update("update device set status = 4 where id = #{deviceId}")
    int updateDeviceStatusToStopScrap(int deviceId);


    //修改报废设备状态
    @Update("update device set status = 0 where id = #{deviceId}")
    int updateDeviceStatusToNormal(int deviceId);

    @Select("select * from scrap")
    List<Scrap> findAll();
}
