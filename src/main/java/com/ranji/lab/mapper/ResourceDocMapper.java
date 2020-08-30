package com.ranji.lab.mapper;

import com.ranji.lab.entity.ResourceDoc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ResourceDocMapper {
    @Insert("insert into resource_doc (url,name) values (#{url},#{name})")
    int insertResourceDoc(ResourceDoc resourceDoc);
    @Update("update resource_doc set url = #{url},name=#{name} where id = #{id}")
    int updateResourceDoc(ResourceDoc resourceDoc);
    @Select("select * from resource_doc")
    List<ResourceDoc> findAll();
    @Select("select * from resource_doc where id = #{id}")
    ResourceDoc findRrsourceDocById(int id);
}
