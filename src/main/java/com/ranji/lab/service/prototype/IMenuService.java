package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.MenuDto;
import com.ranji.lab.entity.Menu;

import java.util.List;

public interface IMenuService {
    int updateMenuService(String menuDto);

    List<Menu> findRootMenu();

    List<Menu> findSonMenuByRootMenuId(int id);
}
