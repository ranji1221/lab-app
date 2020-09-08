package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ConsumeCustodyDto;
import com.ranji.lab.dto.ConsumeCustodyInsertDto;
import com.ranji.lab.entity.ConsumeCustody;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IConsumeCustodyService {

    int insertConsumeCustody(ConsumeCustodyInsertDto consumeCustodyInsertDto);

    int updateConsumeCustody(ConsumeCustody consumeCustody);

    Map<Object,Object> findAll(int pageNum, int pageSize);

    HashMap<Object, Object> findAllConsumeCustodys(int pageNum, int pageSize);

    List<ConsumeCustody> findAll();

    ConsumeCustody findById(int id);

    ConsumeCustodyDto findNameById(int id);

    int getCount();

    //分页模糊查询
    Map<Object, Object> likefindAll(int pageNum, int pageSize, String like);

    //模糊查询
    Map<Object, Object> likefindAll(String like);

    //通过预约项目查询所有耗材和耗材数量
    Map<Object, Object> findAllConsumeAndConsumeNum(int id);

    //按照状态查询保管领用
    Map<Object, Object> statusFindAll(Integer status, int pageNum, int pageSize);

    //修改保管领用
    int updateConsumeCustodyStatus(ConsumeCustodyDto consumeCustodydto);
}
