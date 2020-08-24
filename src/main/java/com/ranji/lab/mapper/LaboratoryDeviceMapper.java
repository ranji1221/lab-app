package com.ranji.lab.mapper;

import com.ranji.lab.entity.LaboratoryDevice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LaboratoryDeviceMapper {
    @Insert("insert into laboratory_device (laboratory_id,device_id,status) values (#{laboratoryId},#{deviceId},#{status})")
    int insertLaboratoryDevice(LaboratoryDevice laboratoryDevice);
    @Update("update laboratory_device set laboratory_id = #{laboratoryId},device_id=#{deviceId},status=#{status} where id = #{id}")
    int updateLaboratoryDevice(LaboratoryDevice laboratoryDevice);
    @Select("select * from laboratory_device")
    List<LaboratoryDevice> allLaboratoryDevice();
}
