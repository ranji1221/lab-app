package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ConsumeCustodyDto;
import com.ranji.lab.entity.ConsumeCustody;

import java.util.List;
import java.util.Map;

public interface IConsumeCustodyService {

    int insertConsumeCustody(ConsumeCustody consumeCustody);

    int updateConsumeCustody(ConsumeCustody consumeCustody);

    Map<Object,Object> findAll(int pageNum, int pageSize);

    List<ConsumeCustody> findAllConsumeCustodys(int pageNum, int pageSize);

    List<ConsumeCustody> findAll();

    ConsumeCustody findById(int id);

    ConsumeCustodyDto findNameById(int id);

    int getCount();

    //分页模糊查询
    Map<Object,Object> likefindAll(int pageNum, int pageSize,String like);

    //模糊查询
    Map<Object,Object> likefindAll(String like);

    //通过预约项目查询所有耗材和耗材数量
    Map<Object,Object> findAllConsumeAndConsumeNum(int id);
}
