package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.MenuDto;
import com.ranji.lab.entity.Menu;

import java.util.List;
import java.util.Map;

public interface IMenuService {
    int updateMenuService(String menuDto);

    List<Menu> findRootMenu();

    List<MenuDto> findSonMenuByRootMenuId(int id);

    Map<String,Object> findAllMenu();
}
