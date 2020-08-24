package com.ranji.lab.mapper;

import com.ranji.lab.entity.ReportRepair;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReportRepairMapper {
    @Insert("insert into report_repair (device_id,status,description,date) values (#{deviceId},#{status},#{description},#{date})")
    int insertReportRepair(ReportRepair reportRepair);
    @Update("update report_repair set device_id = #{deviceId},status=#{status},description = #{description},date=#{date} where id = #{id}")
    int updateReportRepair(ReportRepair reportRepair);
    @Select("select * from report_repair")
    List<ReportRepair> allReportRepair();
}
