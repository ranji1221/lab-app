package com.ranji.lab.service.impl;

import com.ranji.lab.dto.BackStageDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.mapper.BackStageDtoMapper;
import com.ranji.lab.service.prototype.IBackStageDtoService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackStageDtoServiceImpl implements IBackStageDtoService {

    @Resource
    private BackStageDtoMapper backStageDtoMapper;
    @Override
    @Transactional
    public Map<Object,Object> findNowAndLatestSevenDaysData() {
        HashMap<Object, Object> allMap = new HashMap<>();
        List<Map<Object,Object>> all = new ArrayList<>();
        for(int i=0;i<7;i++){
            HashMap<Object, Object> allMap2 = new HashMap<>();
            String nowDays = backStageDtoMapper.findNowDays(i);
            List<BackStageDto> nowDaysLaboratory = backStageDtoMapper.findNowDaysLaboratory(nowDays);
            String date = "";
            for (BackStageDto backStageDto : nowDaysLaboratory) {
                date = backStageDto.getDate();
            }
            allMap2.put("data",nowDaysLaboratory);
            allMap2.put("date",date);
            all.add(allMap2);
        }
        if(!all.isEmpty()){
            allMap.put("data",all);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());

        return allMap;
    }
}
