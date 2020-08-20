package com.ranji.lab.mapper;

import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.entity.Laboratory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LaboratoryMapper {
    @Insert("insert into laboratory (laboratory_name,faculty,laboratory_responsibility) values (#{laboratoryName},#{faculty},#{laboratoryResponsibility})")
    int insertLaboratory(LaboratoryDto laboratoryDto);
    @Update("update laboratory set (laboratory_name=#{laboratoryName},faculty=#{faculty},laboratory_responsibility=#{laboratoryResponsibility}) where id = #{id}")
    int updateLaborytory(Laboratory laboratory);
    @Select("select * from laboratory")
    List<Laboratory> findAll();
}
