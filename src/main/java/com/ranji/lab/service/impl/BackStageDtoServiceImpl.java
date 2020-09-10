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

        double allCount = backStageDtoMapper.findAllCount();
        double finishedCount = backStageDtoMapper.findFinishedCount(nowDays);
        double unfinishedCount = backStageDtoMapper.findUnfinishedCount(nowDays);
        double noCount = backStageDtoMapper.findNoCount(nowDays);

        double allCountPercentageClone = allCount/ allCount;
        double allCountPercentage = Double.parseDouble(df.format(allCountPercentageClone));

        double finishedCountPercentageClone = finishedCount/ allCount;
        double finishedCountPercentage = Double.parseDouble(df.format(finishedCountPercentageClone));

        double unfinishedCountPercentageClone = unfinishedCount/ allCount;
        double unfinishedCountPercentage = Double.parseDouble(df.format(unfinishedCountPercentageClone));

        double noCountPercentageClone = noCount/ allCount;
        double noCountPercentage = Double.parseDouble(df.format(noCountPercentageClone));

        BackStage2Dto backStage2Dto = new BackStage2Dto(allCount,allCountPercentage,finishedCount,finishedCountPercentage,unfinishedCount,unfinishedCountPercentage, noCount,noCountPercentage);
        System.out.println(backStage2Dto);
        allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());

        return allMap;
    }
}
