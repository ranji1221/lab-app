package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.LabInformation;
import com.ranji.lab.mapper.LabInformationMapper;
import com.ranji.lab.service.prototype.ILabInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LabInformationServiceImpl implements ILabInformationService {

    @Resource
    private LabInformationMapper labInformationMapper;
    @Override
    public int insertLabInformation(LabInformation labInformation){
        return labInformationMapper.insertLabInformation(labInformation);
    }

    @Override
    public int updateLabInformation(LabInformation labInformation) {
        return labInformationMapper.insertLabInformation(labInformation);
    }

    @Override
    public List<LabInformation> findAllLabInformation() {
        return labInformationMapper.findAll();
    }

    @Override
    public Map<Object,Object> findAllLabInformation(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LabInformation> allLabInformation = labInformationMapper.findAll();

        PageInfo<LabInformation> allLabInformationPaging = new PageInfo<>(allLabInformation);
        long total = allLabInformationPaging.getTotal();

        Map<Object,Object> allLabInformationOnPaging = new HashMap<>();
        allLabInformationOnPaging.put("data",allLabInformation);
        allLabInformationOnPaging.put("total",total);

        return allLabInformationOnPaging;
    }

    @Override
    public LabInformation findById(int id) {
        return labInformationMapper.findById(id);
    }
}
