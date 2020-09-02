package com.ranji.lab.mapper;

import com.ranji.lab.dto.ReportRepairDto;
import com.ranji.lab.entity.ReportRepair;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReportRepairMapper {
    /**
     * 申请维修
     * @param reportRepair
     * @return
     */
    //插入维修信息
    @Insert("insert into report_repair (device_id,description,date,status) values (#{deviceId},#{description},#{date},0)")
    int insertReportRepair(ReportRepair reportRepair);
    //修改维修设备状态
    @Update("update device set status = 1 where id = #{deviceId}")
    int updateDeviceStatusToStartRepair(int deviceId);

    /**
     * 开始维修
     * @param reportRepair
     * @return
     */
    @Update("update report_repair set device_id = #{deviceId},description = #{description},date=#{date} where id = #{id}")
    int updateReportRepair(ReportRepair reportRepair);
    @Update("update device set status = 3 where id = #{deviceId}")
    int updateDeviceStatusToStopRepair(int deviceId);

    /**
     * 维修结束
     * @return
     */
    @Update("update device set status = 0 where id = #{deviceId}")
    int updateDeviceStatusToNormal(int deviceId);

    /**
     * 查询所有维修设备
     * @return
     */
    @Select("SELECT rr.*, l.laboratory_name, d.uuid FROM report_repair rr LEFT JOIN device d ON d.id = rr.id LEFT JOIN ( SELECT * FROM laboratory_device ) ld ON ld.device_id = rr.device_id LEFT JOIN laboratory l ON l.id = ld.laboratory_id")
    List<ReportRepairDto> allReportRepair();

}
