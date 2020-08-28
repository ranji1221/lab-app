package com.ranji.lab.mapper;

import com.ranji.lab.entity.ResourcePdf;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ResourcePdfMapper {
    @Insert("insert into resource_pdf (url,name) values (#{url},#{name})")
    int insertResourceDoc(ResourcePdf resourcePdf);
    @Update("update resource_pdf set url = #{url},name=#{name} where id = #{id}")
    int updateResourceDoc(ResourcePdf resourcePdf);
    @Select("select * from resource_pdf")
    List<ResourcePdf> findAll();
}
