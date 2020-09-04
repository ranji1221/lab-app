package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ConsumeTypeDto;
import com.ranji.lab.entity.Audit;
import com.ranji.lab.entity.Code;
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

    @Override
    public Map<Object, Object> allConsumeType(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumeType> all = consumeTypeMapper.findAll();

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
}
