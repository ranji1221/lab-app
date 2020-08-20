package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.entity.Arrange;
import com.ranji.lab.mapper.ArrangeMapper;
import com.ranji.lab.service.prototype.IArrangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArrangeServiceImpl implements IArrangeService {
    @Resource
    ArrangeMapper arrangeMapper;
    @Override
    public int insertArrange(Arrange arrange) {
        return arrangeMapper.insertArrange(arrange);
    }

    @Override
    public List<ArrangeDto> findAllArrange() {
        return arrangeMapper.findAllArrange();
    }


    @Override
    public Map<Object, Object> pageFindAllArrange(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArrangeDto> allArrange = arrangeMapper.findAllArrange();
        PageInfo<ArrangeDto> arrangeDtoPageInfo = new PageInfo<>(allArrange);
        long total = arrangeDtoPageInfo.getTotal();
        Map<Object,Object> map = new HashMap<>();
        map.put("data",arrangeDtoPageInfo);
        map.put("total",total);
        return map;
    }

    @Override
    public ArrangeDto idFindArrange(int id) {
        return arrangeMapper.idFindArrange(id);
    }

    @Override
    public int updArrange(Arrange arrange) {
        return arrangeMapper.updArrange(arrange);
    }

    @Override
    public List<ArrangeDto> yesOrNoArrange(Arrange arrange) {
        return null;
    }
}
