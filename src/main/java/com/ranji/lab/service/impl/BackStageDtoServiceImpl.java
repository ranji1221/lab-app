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
    @Transactional
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
    public Map<Object, Object> findSevenDaysAgoDataDemo() {
        int[] arrangeArray = new int[7];
        int[] arrangeDateArray = new int[7];
        int[] arrangeProjectArray = new int[7];
        String[] dateArray = new String[7];
        for (int i = 0; i < 7; i++) {
            String date = backStageDtoMapper.findNowDays(i);
            dateArray[6 - i] = date;
        }
        Integer arrangeNum16 = backStageDtoMapper.findArrangeNum("2020-09-16");
        Integer arrangeDateNum16 = backStageDtoMapper.findArrangeDateNum("2020-09-16");
        Integer arrangeProjectNum16 = backStageDtoMapper.findArrangeProjectNum("2020-09-16");
        arrangeArray[6] = arrangeNum16;
        arrangeDateArray[6] = arrangeDateNum16;
        arrangeProjectArray[6] = arrangeProjectNum16;

        Integer arrangeNum15 = backStageDtoMapper.findArrangeNum("2020-09-15");
        Integer arrangeDateNum15 = backStageDtoMapper.findArrangeDateNum("2020-09-15");
        Integer arrangeProjectNum15 = backStageDtoMapper.findArrangeProjectNum("2020-09-15");
        arrangeArray[5] = arrangeNum15;
        arrangeDateArray[5] = arrangeDateNum15;
        arrangeProjectArray[5] = arrangeProjectNum15;

        Integer arrangeNum14 = backStageDtoMapper.findArrangeNum("2020-09-14");
        Integer arrangeDateNum14 = backStageDtoMapper.findArrangeDateNum("2020-09-14");
        Integer arrangeProjectNum14 = backStageDtoMapper.findArrangeProjectNum("2020-09-14");
        arrangeArray[4] = arrangeNum14;
        arrangeDateArray[4] = arrangeDateNum14;
        arrangeProjectArray[4] = arrangeProjectNum14;

        Integer arrangeNum13 = backStageDtoMapper.findArrangeNum("2020-09-13");
        Integer arrangeDateNum13 = backStageDtoMapper.findArrangeDateNum("2020-09-13");
        Integer arrangeProjectNum13 = backStageDtoMapper.findArrangeProjectNum("2020-09-13");
        arrangeArray[3] = arrangeNum13;
        arrangeDateArray[3] = arrangeDateNum13;
        arrangeProjectArray[3] = arrangeProjectNum13;

        Integer arrangeNum12 = backStageDtoMapper.findArrangeNum("2020-09-12");
        Integer arrangeDateNum12 = backStageDtoMapper.findArrangeDateNum("2020-09-12");
        Integer arrangeProjectNum12 = backStageDtoMapper.findArrangeProjectNum("2020-09-12");
        arrangeArray[2] = arrangeNum12;
        arrangeDateArray[2] = arrangeDateNum12;
        arrangeProjectArray[2] = arrangeProjectNum12;

        Integer arrangeNum11 = backStageDtoMapper.findArrangeNum("2020-09-11");
        Integer arrangeDateNum11 = backStageDtoMapper.findArrangeDateNum("2020-09-11");
        Integer arrangeProjectNum11 = backStageDtoMapper.findArrangeProjectNum("2020-09-11");
        arrangeArray[1] = arrangeNum11;
        arrangeDateArray[1] = arrangeDateNum11;
        arrangeProjectArray[1] = arrangeProjectNum11;

        Integer arrangeNum10 = backStageDtoMapper.findArrangeNum("2020-09-10");
        Integer arrangeDateNum10 = backStageDtoMapper.findArrangeDateNum("2020-09-10");
        Integer arrangeProjectNum10 = backStageDtoMapper.findArrangeProjectNum("2020-09-10");
        arrangeArray[0] = arrangeNum10;
        arrangeDateArray[0] = arrangeDateNum10;
        arrangeProjectArray[0] = arrangeProjectNum10;

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

    @Override
    @Transactional
    public Map<Object, Object> findAllAndFinishedAndUnfinishedAndNoCountDataDemo() {
        HashMap<Object, Object> allMap = new HashMap<>();
        String nowDays = backStageDtoMapper.findNowDays(0);

        DecimalFormat df = new DecimalFormat("######0.00");

        //查询全部实验室
        double allCount = backStageDtoMapper.findAllCount();
        //今天已用过的实验室 = 正在使用的实验室数量 + 已完成的实验室数量
        double useingCount = backStageDtoMapper.findUseingCount("2020-09-16");
        //今天尚未使用的实验室数量
        double noUseCount = backStageDtoMapper.findNoUseCount("2020-09-16");
        //今天要用到的实验室数量
        double willUseCount = backStageDtoMapper.findWillUseCount("2020-09-16");

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
