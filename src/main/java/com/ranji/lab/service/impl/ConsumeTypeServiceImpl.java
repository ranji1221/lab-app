package com.ranji.lab.service.impl;

import com.ranji.lab.dto.ConsumeTypeDto;
import com.ranji.lab.entity.ConsumeType;
import com.ranji.lab.mapper.ConsumeTypeMapper;
import com.ranji.lab.service.prototype.IConsumeTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ConsumeTypeServiceImpl implements IConsumeTypeService {

    @Resource
    private ConsumeTypeMapper consumeTypeMapper;
    @Override
    public int insertConsumeType(ConsumeTypeDto consumeTypeDto) {
        return consumeTypeMapper.insertConsumeType(consumeTypeDto);
    }

    @Override
    public int updateConsumeType(ConsumeType consumeType) {
        return consumeTypeMapper.updateConsumeType(consumeType);
    }

    @Override
    public Map<Object, Object> allConsumeType() {
        List<ConsumeType> all = consumeTypeMapper.findAll();
        Map<Object,Object> allConsumeTypeMap = new HashMap<>();
        allConsumeTypeMap.put("data",all);
        return allConsumeTypeMap;
    }
}
