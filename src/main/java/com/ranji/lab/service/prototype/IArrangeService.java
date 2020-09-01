package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.entity.Arrange;

import java.util.List;
import java.util.Map;

public interface IArrangeService {
    //新增
    int insertArrange(Arrange arrange,String devices,String consumes);

    //查询全部
    List<ArrangeDto> findAllArrange(Integer status);
    //分页查询全部
    Map<Object,Object> pageFindAllArrange(int pageNum,int pageSize,Integer status);
    //按照id查询项目
    ArrangeDto idFindArrange(int id);
    //修改该项目信息
    int updArrange(Arrange arrange,String devices,String consumes);

    //验证是否可以预约
    List<ArrangeDto>yesOrNoArrange(Arrange arrange);

    //模糊查询
    Map<Object,Object> pageFindlikeFindArrange(String like);
}
