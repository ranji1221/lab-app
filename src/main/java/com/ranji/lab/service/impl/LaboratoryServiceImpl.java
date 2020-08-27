package com.ranji.lab.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.entity.Laboratory;
import com.ranji.lab.mapper.LaboratoryMapper;
import com.ranji.lab.service.prototype.ILaboratoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaboratoryServiceImpl implements ILaboratoryService {

    @Resource
    private LaboratoryMapper laboratoryMapper;
    @Override
    public int insertLaboratory(LaboratoryDto laboratoryDto) {

        return laboratoryMapper.insertLaboratory(laboratoryDto);
    }

    @Override
    public int updateLaboratory(Laboratory laboratory) {
        return laboratoryMapper.updateLaborytory(laboratory);
    }

    @Override
    public Map<Object,Object> findAllLaboratory() {
        List<Laboratory> all = laboratoryMapper.findAll();
        Map<Object,Object> allLaboratory = new HashMap<>();
        allLaboratory.put("data",all);
        return allLaboratory;
    }


    @Override
    public List<Laboratory> dateFindAll(String date, String timeStart, String timeStop) {
        return laboratoryMapper.dateFindAll(date,timeStart,timeStop);
    }

    @Override
    public Map<Object, Object> findAllLaboratory(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Laboratory> all = laboratoryMapper.findAll();
        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        Map<Object,Object> allMap =  new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);

        return allMap;

    }
}
