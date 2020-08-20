package com.ranji.lab.mapper;

import com.ranji.lab.dto.ConsumeTypeDto;
import com.ranji.lab.entity.ConsumeType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ConsumeTypeMapper {
    @Insert("insert into consume_type (consume_name) values (#{consumeName})")
    int insertConsumeType(ConsumeTypeDto consumeTypeDto);
    @Update("update consume_type set (consume_name=consumeName) where id = #{id}")
    int updateConsumeType(ConsumeType consumeType);
    @Select("select * from consume_type")
    List<ConsumeType> findAll();
}
