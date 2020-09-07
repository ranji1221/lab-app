package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ScrapDto;
import com.ranji.lab.dto.ScrapInsertDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.ReportRepair;
import com.ranji.lab.entity.Scrap;
import com.ranji.lab.mapper.DeviceMapper;
import com.ranji.lab.mapper.ScrapMapper;
import com.ranji.lab.service.prototype.IScrapService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScrapServiceImpl implements IScrapService {

    @Resource
    private ScrapMapper scrapMapper;
    @Resource
    private DeviceMapper deviceMapper;

    @Override
    @Transactional
    public int insertScrap(ScrapInsertDto scrapInsertDto) {
        String uuid = scrapInsertDto.getUuid();
        ArrayList<String> strings = JSON.parseObject(uuid, new TypeReference<ArrayList<String>>(){});

        for (String s : strings) {
            List<Device> deviceByuuid = deviceMapper.findDeviceByuuid(s);
            if(deviceByuuid.isEmpty()){
                return 0;
            }
        }
        for (String s : strings) {
            int deviceId = deviceMapper.findDeviceIdByuuid(s);
            Scrap scrap = new Scrap();
            scrap.setDeviceId(deviceId);
            scrap.setDate(scrapInsertDto.getDate());
            scrap.setDescription(scrapInsertDto.getDescription());
            scrapMapper.insertScarp(scrap);
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
        List<ScrapDto> all = scrapMapper.findAll();
        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty()){
            allMap.put("data",all);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return allMap;
    }

    @Override
    public Map<Object, Object> allScrap(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ScrapDto> all = scrapMapper.findAll();

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty()){
            allMap.put("data",all);
            allMap.put("total",total);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return allMap;
    }

    @Override
    public Map<Object, Object> likeFindAll(String like) {
        List<ScrapDto> all = scrapMapper.likeFindAll(like);
        HashMap<Object, Object> allMap = new HashMap<>();
        if (!all.isEmpty()) {
            allMap.put("data", all);
            allMap.put("total", all.size());
            allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        } else
            allMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
        return allMap;
    }

    @Override
    public Map<Object, Object> statusFindScrap(Integer status, int page, int limit) {
        List<ScrapDto> all = scrapMapper.statusFindScrap(status);
        HashMap<Object, Object> allMap = new HashMap<>();
        if (!all.isEmpty()) {
            allMap.put("data", all);
            allMap.put("total", all.size());
            allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        } else
            allMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
        return allMap;
    }
}
