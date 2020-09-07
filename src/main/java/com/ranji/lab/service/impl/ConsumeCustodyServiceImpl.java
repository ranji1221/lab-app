package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.*;
import com.ranji.lab.entity.ConsumeCustody;
import com.ranji.lab.mapper.ConsumeCustodyMapper;
import com.ranji.lab.mapper.ExperimentProjectMapper;
import com.ranji.lab.mapper.ProjectConsumeMapper;
import com.ranji.lab.mapper.ProjectDeviceMapper;
import com.ranji.lab.service.prototype.IConsumeCustodyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ConsumeCustodyServiceImpl implements IConsumeCustodyService {
    @Resource
    private ConsumeCustodyMapper consumeCustodyMapper;
    @Resource
    private ExperimentProjectMapper experimentProjectMapper;
    @Resource
    ProjectConsumeMapper projectConsumeMapper;
    @Resource
    ProjectDeviceMapper projectDeviceMapper;

    @Override
    public int insertConsumeCustody(ConsumeCustodyInsertDto consumeCustodyInsertDto) {
        return consumeCustodyMapper.insertConsumeCustody(consumeCustodyInsertDto);
    }

    @Override
    public int updateConsumeCustody(ConsumeCustody consumeCustody) {
        return consumeCustodyMapper.updateConsumeCustody(consumeCustody);
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

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total" , total);

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

    @Override
    public Map<Object, Object> likefindAll(String like) {
        List<ConsumeCustody> consumeCustodies = consumeCustodyMapper.likefindAll(like);
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data",consumeCustodies);
        allMap.put("tatol",consumeCustodies.size());
        return allMap;
    }

    /**
     * 通过项目查询所有的耗材和耗材数量
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Map<Object,Object> findAllConsumeAndConsumeNum(int id) {
        //输出map
        HashMap<Object, Object> allMap = new HashMap<>();
        //接收list
        List<ConsumeAndConsumeNumDto> allList = new ArrayList<>();

        //拿到实验项目
        ExperimentProjectDto experimentProjectDto = experimentProjectMapper.idFindExperimentProject(id);
        experimentProjectDto.setProjectDeviceList(projectDeviceMapper.projectIdFindProjectDeviceNum(id));
        experimentProjectDto.setProjectConsumeList(projectConsumeMapper.projectIdFindAllProjectConsume(id));
        //获得项目中的耗材清单
        List<ProjectConsumeDto> projectConsumeList = experimentProjectDto.getProjectConsumeList();
        for (ProjectConsumeDto projectConsumeDto : projectConsumeList) {
            ConsumeAndConsumeNumDto consumeAndConsumeNumDto = new ConsumeAndConsumeNumDto();
            consumeAndConsumeNumDto.setConsumeId(projectConsumeDto.getExperimentConsumeId());
            consumeAndConsumeNumDto.setName(projectConsumeDto.getConsumeName());
            consumeAndConsumeNumDto.setNum(projectConsumeDto.getConsumeNum());
            allList.add(consumeAndConsumeNumDto);
        }
        allMap.put("data", allList);
        return allMap;
    }

    @Override
    public Map<Object, Object> statusFindAll(Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConsumeCustody> all = consumeCustodyMapper.statusFindAll(status);

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data", all);
        allMap.put("total", total);

        return allMap;
    }
}
