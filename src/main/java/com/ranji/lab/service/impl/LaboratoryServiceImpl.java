package com.ranji.lab.service.impl;


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
}
