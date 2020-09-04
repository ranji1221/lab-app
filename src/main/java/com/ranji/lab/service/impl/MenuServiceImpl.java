package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ranji.lab.dto.MenuDto;
import com.ranji.lab.dto.ProjectDeviceDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Menu;
import com.ranji.lab.entity.Role;
import com.ranji.lab.mapper.MenuMapper;
import com.ranji.lab.service.prototype.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private MenuMapper menuMapper;
    @Override
    @Transactional
    public int updateMenuService(String menuDto) {
        List<MenuDto> menuDtos = JSON.parseObject(menuDto, new TypeReference<ArrayList<MenuDto>>() {});
        for (MenuDto menu : menuDtos) {
            int i = menuMapper.updateMenu(menu);
            if(i<1) return 0;
        }
        return 1;
    }

    @Override
    public List<Menu> findRootMenu() {
        return menuMapper.findRootMenu();
    }

    @Override
    public List<MenuDto> findSonMenuByRootMenuId(int id) {
        return menuMapper.findSonMenuByRootMenuId(id);
    }

    @Override
    public Map<String, Object> findAllMenu() {
        List<MenuDto> sonMenuByRootMenuId = menuMapper.findSonMenuByRootMenuId(0);
        for (MenuDto menuDto : sonMenuByRootMenuId) {
            List<MenuDto> sonMenuByRootMenuId1 = menuMapper.findSonMenuByRootMenuId(menuDto.getId());
            menuDto.setMenu(sonMenuByRootMenuId1);
            System.out.println(menuDto);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",sonMenuByRootMenuId);
        return map;
    }


}
