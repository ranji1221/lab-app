package com.ranji.lab.mapper;

import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.LabInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LabInformationMapper {
    @Insert("insert into lab_information (ltitle,lfaculty,ldevice,lteacher,ldate,ltime,lstatus) values (#{lid},#{ltitle},#{lfaculty},#{ldevice},#{lteacher},#{ldate},#{ltime},#{lstatus});")
    int insertLabInformation(LabInformation labInformation);
    @Update("update lab_information set lid=#{lid},ltitle=#{ltitle},lfaculty=#{lfaculty},ldevice=#{ldevice,},lteacher=#{lteacher},ldate=#{ldate},ltime=#{ltime},lstatus=#{lstatus} where lid = #{lid}")
    int updateLabInformation(LabInformation labInformation);

    @Select("select lab_information.* from lab_information order by ldate desc")
    List<LabInformation> findAll();
    @Select("select lab_information.* from lab_information where lab_information.id = #{id}")
    Device findById(int id);
}
