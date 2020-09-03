package com.ranji.lab.service.prototype;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ConsumeInformAndConsumeTypeNameDto;
import com.ranji.lab.dto.ConsumeInformDto;
import com.ranji.lab.entity.ConsumeCustody;
import com.ranji.lab.entity.ConsumeInform;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IConsumeInformService {

    int insertConsumeInform(ConsumeInformDto consumeInformDto);

    int updateConsumeInform(ConsumeInform consumeInform);

    List<ConsumeInform> findAllConsumeInform();

    Map<Object,Object> findAllConsumeInform(int pageNum, int pageSize);

    ConsumeInform findConsumeInformById(int id);

    Map<Object,Object> findAllConfumeInformByTypeId(int type);

    List<ConsumeInformAndConsumeTypeNameDto> findConsumeAndConsumeName();

    Map<Object, Object> pageFindConsumeAndConsumeName(int pageNum, int pageSize);

    List<ConsumeInform> arrangeProjectIdFindconsumeInform(int arrangeProjectId);

    //分页模糊查询耗材
    Map<Object,Object> pageLikeFindConsumeAndConsumeName(int pageNum, int pageSize,String like);

    //模糊查询耗材
    Map<Object,Object> pageLikeFindConsumeAndConsumeName(String like);

}