package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ConsumeInformDto;
import com.ranji.lab.entity.ConsumeCustody;
import com.ranji.lab.entity.ConsumeInform;

import java.util.List;
import java.util.Map;

public interface IConsumeInformService {

    int insertConsumeInform(ConsumeInformDto consumeInformDto);

    int updateConsumeInform(ConsumeInform consumeInform);

    List<ConsumeInform> findAllConsumeInform();

    Map<Object,Object> findAllConsumeInform(int pageNum, int pageSize);

    ConsumeInform findConsumeInformById(int id);
}
