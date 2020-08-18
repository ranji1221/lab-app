package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Monitaring;
import com.ranji.lab.mapper.MonitaringMapper;
import com.ranji.lab.service.prototype.IMonitaringService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonitaringServiceImpl implements IMonitaringService {

    @Resource
    private MonitaringMapper monitaringMapper;

    @Override
    public int insertMonitaring(Monitaring monitaring) {
        return monitaringMapper.insertMonitaring(monitaring);
    }

    @Override
    public Map<Object,Object> findAllMonitaring(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Monitaring> allMonitaring = monitaringMapper.findAll();

        PageInfo allMonitaringPaging = new PageInfo(allMonitaring);
        long total = allMonitaringPaging.getTotal();

        Map<Object,Object> allMonitaringOnPaging = new HashMap<>();
        allMonitaringOnPaging.put("data",allMonitaring);
        allMonitaringOnPaging.put("total",total);
        return allMonitaringOnPaging;
    }

    @Override
    public List<Monitaring> findAllMonitaring() {
        return monitaringMapper.findAll();
    }

    @Override
    public Monitaring findById(int id) {
        return monitaringMapper.findById(id);
    }

    @Override
    public int updateMonitaring(Monitaring monitaring) {
        return monitaringMapper.updateMonitaring(monitaring);
    }
}
