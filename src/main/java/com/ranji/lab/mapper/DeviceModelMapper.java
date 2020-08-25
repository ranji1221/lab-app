package com.ranji.lab.mapper;

import com.ranji.lab.entity.DeviceModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DeviceModelMapper {
    //插入
    @Insert("insert into device_model (device_name,brand,facid,proid,supid,type,lifetime,count,unit_name) values (#{deviceName},#{brand},#{facid},#{proid},#{supid},#{type},#{lifetime},#{count},#{unitName})")
    int insertDeviceModel(DeviceModel deviceModel);
    //修改
    @Update("update device_model set device_name = #{deviceName},brand=#{brand},facid=#{facid},proid=#{proid},supid=#{supid},type=#{type},lifetime=#{lifetime},count = count + #{count},unit_name=#{unitName} where id = #{id}")
    int updateDeviceModel(DeviceModel deviceModel);
    //查询全部
    @Select("select * from device_model")
    List<DeviceModel> allDeviceModel();
    //按照类型查询
    @Select("select * from device_model dm where dm.type = #{type}")
    List<DeviceModel> allDeviceModelByType(int type);
    //根据设备名字和型号判断是否存在
    @Select("select * from device_model where device_name=#{deviceName} and brand = #{brand}")
    List<DeviceModel> ifExistsThisDeviceModel(String deviceName,String brand);
    @Select("select Max(id) from device_model")
    int findLatestDeviceModelId();
    @Select("select id from device_model where device_name=#{deviceName} and brand = #{brand}")
    int findDeviceModelIdByDeviceNameAndBrand(String deviceName,String brand);
}
