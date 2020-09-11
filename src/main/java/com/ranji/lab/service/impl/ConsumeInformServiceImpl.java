package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ConsumeInformAndConsumeTypeNameDto;
import com.ranji.lab.dto.ConsumeInformDto;
import com.ranji.lab.entity.ConsumeCustody;
import com.ranji.lab.entity.ConsumeInform;
import com.ranji.lab.mapper.ConsumeCustodyMapper;
import com.ranji.lab.mapper.ConsumeInformMapper;
import com.ranji.lab.service.prototype.IConsumeInformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ConsumeInformServiceImpl implements IConsumeInformService {

    @Resource
    private ConsumeInformMapper consumeInformMapper;

    @Override
    public int insertConsumeInform(ConsumeInformDto consumeInformDto) {
        return consumeInformMapper.insertConsumeInform(consumeInformDto);
    }

    @Override
    public int updateConsumeInform(ConsumeInform consumeInform) {
        return consumeInformMapper.updateConsumeInform(consumeInform);
    }

    @Override
    public List<ConsumeInform> findAllConsumeInform() {
        return consumeInformMapper.findAll();
    }

    @Override
    public Map<Object, Object> findAllConsumeInform(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumeInform> allConsumeInform = consumeInformMapper.findAll();

        PageInfo<ConsumeInform> allConsumeInformPaging = new PageInfo<>(allConsumeInform);
        long total = allConsumeInformPaging.getTotal();

        Map<Object,Object> allConsumeInformOnPaging = new HashMap<>();
        allConsumeInformOnPaging.put("data",allConsumeInform);
        allConsumeInformOnPaging.put("total",total);
        return allConsumeInformOnPaging;
    }

    @Override
    public ConsumeInform findConsumeInformById(int id) {
        return consumeInformMapper.findById(id);
    }

    @Override
    public Map<Object,Object> findAllConfumeInformByTypeId(int type) {
        List<ConsumeInformDto> allConsumeInformByTypeId = consumeInformMapper.findAllConsumeInformByTypeId(type);
        Map<Object,Object> allConsumeInformByTypeIdMap = new HashMap<>();
        allConsumeInformByTypeIdMap.put("data",allConsumeInformByTypeId);
        return allConsumeInformByTypeIdMap;
    }

    @Override
    public List<ConsumeInformAndConsumeTypeNameDto> findConsumeAndConsumeName() {
        return consumeInformMapper.findConsumeAndConsumeName();
    }

    @Override
    public Map<Object, Object> pageFindConsumeAndConsumeName(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumeInformAndConsumeTypeNameDto> consumeAndConsumeName = consumeInformMapper.findConsumeAndConsumeName();


        PageInfo pageInfo = new PageInfo(consumeAndConsumeName);
        long total = pageInfo.getTotal();

        Map<Object, Object> allMap= new HashMap<>();
        allMap.put("data",consumeAndConsumeName);
        allMap.put("total",total);

        return allMap;
    }

    @Override
    public List<ConsumeInform> arrangeProjectIdFindconsumeInform(int arrangeProjectId) {
        return consumeInformMapper.arrangeProjectIdFindconsumeInform(arrangeProjectId);
    }

    @Override
    public Map<Object, Object> pageLikeFindConsumeAndConsumeName(int pageNum, int pageSize,String like) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConsumeInformAndConsumeTypeNameDto> consumeInformAndConsumeTypeNameDtos = consumeInformMapper.likeFindConsumeAndConsumeName(like);

        PageInfo pageInfo = new PageInfo(consumeInformAndConsumeTypeNameDtos);
        long total = pageInfo.getTotal();

        Map<Object, Object> allMap = new HashMap<>();
        allMap.put("data", consumeInformAndConsumeTypeNameDtos);
        allMap.put("total", total);

        return allMap;
    }

    @Override
    public Map<Object, Object> pageLikeFindConsumeAndConsumeName(String like) {
        List<ConsumeInformAndConsumeTypeNameDto> consumeInformAndConsumeTypeNameDtos = consumeInformMapper.likeFindConsumeAndConsumeName(like);
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data", consumeInformAndConsumeTypeNameDtos);
        allMap.put("total", consumeInformAndConsumeTypeNameDtos.size());
        return allMap;
    }
}
