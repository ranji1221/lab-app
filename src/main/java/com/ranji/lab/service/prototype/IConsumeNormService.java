package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ConsumeNormDto;
import com.ranji.lab.entity.ConsumeNorm;

import java.util.List;
import java.util.Map;

public interface IConsumeNormService {

    int insertConsumeNorm(ConsumeNormDto consumeNormDto);

    int updateConsumeNorm(ConsumeNorm consumeNorm);

    ConsumeNorm findAll();

    ConsumeNorm findById(int id);

    Map<Object,Object> findAllConsumeNormPaging(int pageNum,int pageSize);
}
