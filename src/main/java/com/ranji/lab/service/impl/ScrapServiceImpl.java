package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Scrap;
import com.ranji.lab.mapper.ScrapMapper;
import com.ranji.lab.service.prototype.IScrapService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScrapServiceImpl implements IScrapService {

    @Resource
    private ScrapMapper scrapMapper;
    @Override
    @Transactional
    public int insertScrap(Scrap scrap) {
        int i = scrapMapper.insertScarp(scrap);
        if(1<0) return 0;
        else{
            int i1 = scrapMapper.updateDeviceStatusToStartScrap(scrap.getId());
            if(i1<0) return 0;
        }
        return 1;
    }

    @Override
    public int updateScrapValue(Scrap scrap) {
        return scrapMapper.updateScarp(scrap);
    }

    @Override
    @Transactional
    public int updateScrapStatus(Scrap scrap) {
        int i = scrapMapper.updateScarp(scrap);
        if(i<0) return 0;
        else{
            int i1 = scrapMapper.updateDeviceStatusToStopScrap(scrap.getId());
            if(i1<0) return 0;
        }
        return 1;
    }

    @Override
    public Map<Object, Object> allScrap() {
        List<Scrap> all = scrapMapper.findAll();
        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty()){
            allMap.put("data",all);
            allMap.put(Code.SUCCESS.getCode(),Code.SUCCESS.getMsg());
        }else
            allMap.put(Code.FAILURE.getCode(),Code.FAILURE.getMsg());
        return allMap;
    }

    @Override
    public Map<Object, Object> allScrap(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Scrap> all = scrapMapper.findAll();

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty()){
            allMap.put("data",all);
            allMap.put("total",total);
            allMap.put(Code.SUCCESS.getCode(),Code.SUCCESS.getMsg());
        }else
            allMap.put(Code.FAILURE.getCode(),Code.FAILURE.getMsg());
        return allMap;
    }
}
