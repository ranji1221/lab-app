package com.ranji.lab.mapper;

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
    @Insert("insert into report_repair (device_id,description,date) values (#{deviceId},#{description},#{date})")
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
    @Select("select * from report_repair")
    List<ReportRepair> allReportRepair();
}