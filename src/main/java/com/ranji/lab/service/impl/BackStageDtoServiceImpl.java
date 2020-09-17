package com.ranji.lab.service.impl;

import com.ranji.lab.dto.BackStage2Dto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.mapper.BackStageDtoMapper;
import com.ranji.lab.service.prototype.IBackStageDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class BackStageDtoServiceImpl implements IBackStageDtoService {

    @Resource
    private BackStageDtoMapper backStageDtoMapper;

    @Override
    public Map<Object, Object> findSevenDaysAgoData() {
        int[] arrangeArray = new int[7];
        int[] arrangeDateArray = new int[7];
        int[] arrangeProjectArray = new int[7];
        String[] dateArray = new String[7];
        for (int i = 0; i < 7; i++) {
            String date = backStageDtoMapper.findNowDays(i);
            Integer arrangeNum = backStageDtoMapper.findArrangeNum(date);
            Integer arrangeDateNum = backStageDtoMapper.findArrangeDateNum(date);
            Integer arrangeProjectNum = backStageDtoMapper.findArrangeProjectNum(date);
            if (arrangeNum != null) {
                arrangeArray[6 - i] = arrangeNum;
            }
            if (arrangeDateNum != null) {
                arrangeDateArray[6 - i] = arrangeDateNum;
            }
            if (arrangeProjectNum != null) {
                arrangeProjectArray[6 - i] = arrangeProjectNum;
            }
            dateArray[6 - i] = date;
        }
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("arrange", arrangeArray);
        allMap.put("arrangeDate", arrangeDateArray);
        allMap.put("arrangeProject", arrangeProjectArray);
        allMap.put("Date", dateArray);
        allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        return allMap;
    }

    @Override
    @Transactional
    public Map<Object, Object> findAllAndFinishedAndUnfinishedAndNoCountData() {
        HashMap<Object, Object> allMap = new HashMap<>();
        String nowDays = backStageDtoMapper.findNowDays(0);

        DecimalFormat df = new DecimalFormat("######0.00");

        //查询全部实验室
        double allCount = backStageDtoMapper.findAllCount();
        //今天已用过的实验室 = 正在使用的实验室数量 + 已完成的实验室数量
        double useingCount = backStageDtoMapper.findUseingCount(nowDays);
        //今天尚未使用的实验室数量
        double noUseCount = backStageDtoMapper.findNoUseCount(nowDays);
        //今天要用到的实验室数量
        double willUseCount = backStageDtoMapper.findWillUseCount(nowDays);

        double allCountPercentageClone = allCount / allCount;
        double allCountPercentage = Double.parseDouble(df.format(allCountPercentageClone));

        double useingCountPercentageClone = useingCount / willUseCount;
        double useingCountPercentage = 0;
        double noUseCountPercentage = 0;
        double willUseCountPercentage = 0;
        if (useingCountPercentageClone > 0) {
            useingCountPercentage = Double.parseDouble(df.format(useingCountPercentageClone));
        }

        double noUseCountPercentageClone = noUseCount / willUseCount;
        if (noUseCountPercentageClone > 0) {
            noUseCountPercentage = Double.parseDouble(df.format(noUseCountPercentageClone));
        }

        double willUseCountPercentageClone = willUseCount / allCount;
        if (willUseCountPercentageClone > 0) {
            willUseCountPercentage = Double.parseDouble(df.format(willUseCountPercentageClone));
        }
        BackStage2Dto backStage2Dto = new BackStage2Dto(allCount, allCountPercentage, useingCount, useingCountPercentage, noUseCount, noUseCountPercentage, willUseCount, willUseCountPercentage);

        allMap.put("data", backStage2Dto);
        allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());

        return allMap;
    }
}
