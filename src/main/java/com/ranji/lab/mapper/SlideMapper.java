package com.ranji.lab.mapper;

import com.ranji.lab.entity.Slide;
import org.apache.ibatis.annotations.*;

/**
 * 轮播图Mapper类
 * 主要指图片资源
 */
public interface SlideMapper {
    @Insert("insert into t_slide(id,name,url) values(#{id},#{name},#{url})")
    void save(Slide slide);

    @Select("select * from t_slide where id=#{id}")
    Slide findByID(@Param("id") int id);

    @Update("update t_slide set name=#{name},url=#{url} where id=#{id}")
    void update(Slide slide);
}
