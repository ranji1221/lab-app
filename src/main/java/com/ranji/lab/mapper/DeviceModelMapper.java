package com.ranji.lab.mapper;

import com.ranji.lab.entity.DeviceModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DeviceModelMapper {
    //插入
    @Insert("insert into device_model (device_name,brand,facid,proid,supid,type,lifetime,count,unit_name) values (#{deviceName},#{brand},#{facid},#{proid},#{supid},#{type},#{lifetime},#{unitName})")
    int insertDeviceModel(DeviceModel deviceModel);
    //修改
    @Update("update device_model set device_name = #{deviceName},brand=#{brand},facid=#{facid},proid=#{proid},supid=#{supid},type=#{type},lifetime=#{lifetime},count=#{count},unit_name=#{unitName} where id = #{id}")
    int updateDeviceModel(DeviceModel deviceModel);
    //查询全部
    @Select("select * from device_model")
    List<DeviceModel> allDeviceModel();
    //按照类型查询
    @Select("select * from device_model dm where dm.type = #{type}")
    List<DeviceModel> allDeviceModelByType(int type);
}
