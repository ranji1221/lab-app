package com.ranji.lab.service.impl;

import com.ranji.lab.entity.ConsumeNorm;
import com.ranji.lab.mapper.ConsumeNormContentMapper;
import com.ranji.lab.mapper.ConsumeNormMapper;
import com.ranji.lab.service.prototype.IConsumeNormContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ConsumeNormContentServiceImpl implements IConsumeNormContentService {
    @Resource
    ConsumeNormMapper consumeNormMapper;
    @Resource
    ConsumeNormContentMapper consumeNormContentMapper;
    @Override
    public Map<Object, Object> allConsumeContent() {
        Map<Object,Object> data = new HashMap<>();
        List<List<Object>> allData = new ArrayList<>();


        List<ConsumeNorm> all = consumeNormMapper.findAll();
        for (ConsumeNorm consumeNorm : all) {
            Map<String,Object> map = new HashMap<>();
            List<Object> list = new ArrayList<>();

            int contentId = consumeNorm.getId();
            List<String> content = consumeNormContentMapper.findContentById(contentId);

            map.put("consumeNorm",consumeNorm);
            map.put("content",content);

            list.add(map);
            allData.add(list);
        }


        data.put("data",allData);
        return data;
    }

    @Override
    public int maxConsumeContentId() {
        return consumeNormContentMapper.findMaxContentId();
    }


}
