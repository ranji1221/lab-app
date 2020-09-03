package com.ranji.lab.mapper;

import com.ranji.lab.entity.LaboratoryDevice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LaboratoryDeviceMapper {
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty = "id")
    @Insert("insert into laboratory_device (laboratory_id,device_id,status) values (#{laboratoryId},#{deviceId},0)")
    int insertLaboratoryDevice(LaboratoryDevice laboratoryDevice);
    @Update("update laboratory_device set laboratory_id = #{laboratoryId},device_id=#{deviceId},status=#{status} where id = #{id}")
    int updateLaboratoryDevice(LaboratoryDevice laboratoryDevice);
    @Select("select * from laboratory_device")
    List<LaboratoryDevice> allLaboratoryDevice();
    //按照实验室id、设备型号和需要数量查询实验室对应设备信息
    @Select("SELECT * FROM laboratory_device WHERE STATUS=0 AND laboratory_id= #{laboratoryId} AND device_id IN (SELECT id FROM device WHERE device_model_id= #{deviceModelId}) LIMIT #{pageSize}")
    List<LaboratoryDevice> findLaboratoryDevice(int laboratoryId,int deviceModelId,int pageSize);
}