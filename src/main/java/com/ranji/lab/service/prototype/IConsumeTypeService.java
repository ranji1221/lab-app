package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ConsumeTypeDto;
import com.ranji.lab.entity.ConsumeType;

import java.util.Map;

public interface IConsumeTypeService {
    int insertConsumeType(ConsumeTypeDto consumeTypeDto);

    int updateConsumeType(ConsumeType consumeType);

    Map<Object,Object> allConsumeType();
    Map<Object,Object> allConsumeType(int pageNum,int pageSize);
}
