package com.ranji.lab.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ConsumeNormContentMapper {
    @Select("select content from consume_norm_content where content_id = #{id}")
    List<String> findContentById(int id);
    @Select("select max(id) from consume_norm_content")
    int findMaxContentId();
}
