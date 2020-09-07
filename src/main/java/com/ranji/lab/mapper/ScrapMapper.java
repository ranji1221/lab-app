package com.ranji.lab.mapper;

import com.ranji.lab.dto.ScrapDto;
import com.ranji.lab.entity.Scrap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 设备报废
 */
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

    @Select("SELECT l.laboratory_name, s.*, d.uuid FROM scrap s LEFT JOIN device d ON d.id = s.id LEFT JOIN ( SELECT * FROM laboratory_device ) ld ON ld.device_id = s.device_id LEFT JOIN laboratory l ON l.id = ld.laboratory_id")
    List<ScrapDto> findAll();

    @Select("SELECT l.laboratory_name, s.*, d.uuid FROM scrap s LEFT JOIN device d ON d.id = s.id LEFT JOIN ( SELECT * FROM laboratory_device ) ld ON ld.device_id = s.device_id LEFT JOIN laboratory l ON l.id = ld.laboratory_id where s.status = #{status} ")
    List<ScrapDto> statusFindScrap(Integer status);

    //模糊查询
    @Select("SELECT l.laboratory_name, s.*, d.uuid FROM scrap s LEFT JOIN device d ON d.id = s.id LEFT JOIN ( SELECT * FROM laboratory_device ) ld ON ld.device_id = s.device_id LEFT JOIN laboratory l ON l.id = ld.laboratory_id " +
            " where l.laboratory_name like '%${like}%' or " +
            " s.description like '%${like}%'")
    List<ScrapDto> likeFindAll(String like);
}
