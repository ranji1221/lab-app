package com.ranji.lab.mapper;

import com.ranji.lab.dto.MenuDto;
import com.ranji.lab.entity.Menu;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MenuMapper {
    @Update("update menu set name = #{name},priority = #{priority} where id = #{id}")
    int updateMenu(MenuDto menuDto);

    @Select("select * from menu where pid = 0")
    List<Menu> findRootMenu();

    @Select("select * from menu where pid = #{id}")
    List<Menu> findSonMenuByRootMenuId(int id);
}
