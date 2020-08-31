package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ConsumeCustodyDto;
import com.ranji.lab.entity.ConsumeCustody;
import com.ranji.lab.mapper.ConsumeCustodyMapper;
import com.ranji.lab.service.prototype.IConsumeCustodyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ConsumeCustodyServiceImpl implements IConsumeCustodyService {
    @Resource
    private ConsumeCustodyMapper consumeCustodyMapper;

    @Override
    public int insertConsumeCustody(ConsumeCustody consumeCustody) {
        return consumeCustodyMapper.insertConsumeCustody(consumeCustody);
    }

    @Override
    public int updateConsumeCustody(ConsumeCustody consumeCustody) {
        return consumeCustodyMapper.insertConsumeCustody(consumeCustody);
    }

    @Override
    public Map<Object, Object> findAll(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<ConsumeCustody> allConsumeCustody= consumeCustodyMapper.findAll();

        PageInfo<ConsumeCustody> allConsumeCustodyPaging = new PageInfo<>(allConsumeCustody);
        long total = allConsumeCustodyPaging.getTotal();

        Map<Object, Object> allConsumeCustodyOnPaging = new HashMap<>();
        allConsumeCustodyOnPaging.put("data",allConsumeCustody);
        allConsumeCustodyOnPaging.put("total",total);
        return allConsumeCustodyOnPaging;
    }

    @Override
    public List<ConsumeCustody> findAllConsumeCustodys(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumeCustody> all = consumeCustodyMapper.findAll();
        return all;
    }

    @Override
    public List<ConsumeCustody> findAll() {
        return consumeCustodyMapper.findAll();
    }

    @Override
    public ConsumeCustody findById(int id) {
        return consumeCustodyMapper.findById(id);
    }

    @Override
    public ConsumeCustodyDto findNameById(int id) {
        return consumeCustodyMapper.findNameById(id);
    }

    @Override
    public int getCount() {
        return consumeCustodyMapper.getCount();
    }

    @Override
    public Map<Object, Object> likefindAll(int pageNum, int pageSize, String like) {
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumeCustody> consumeCustodies = consumeCustodyMapper.likefindAll(like);
        PageInfo<ConsumeCustody> allConsumeCustodyPaging = new PageInfo<>(consumeCustodies);
        long total = allConsumeCustodyPaging.getTotal();

        Map<Object, Object> allConsumeCustodyOnPaging = new HashMap<>();
        allConsumeCustodyOnPaging.put("data",consumeCustodies);
        allConsumeCustodyOnPaging.put("total",total);
        return allConsumeCustodyOnPaging;
    }
}
