package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.ConsumeCustody;

import java.util.List;
import java.util.Map;

public interface IConsumeCustodyService {

    int insertConsumeCustody(ConsumeCustody consumeCustody);

    int updateConsumeCustody(ConsumeCustody consumeCustody);

    Map<Object,Object> findAll(int pageNum,int pageSize);

    List<ConsumeCustody> findAll();

    ConsumeCustody findById(int id);
}
