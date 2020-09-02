package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ResourceDoc;
import com.ranji.lab.mapper.ResourceDocMapper;
import com.ranji.lab.service.prototype.IResourceDocService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceDocServiceImpl implements IResourceDocService {

    @Resource
    private ResourceDocMapper resourceDocMapper;

    @Override
    public int insertResourceDoc(ResourceDoc resourceDoc) {
        return resourceDocMapper.insertResourceDoc(resourceDoc);
    }

    @Override
    public int updateResourceDoc(ResourceDoc resourceDoc) {
        return resourceDocMapper.updateResourceDoc(resourceDoc);
    }

    @Override
    public Map<Object, Object> ResourceDocInTen() {
        PageHelper.startPage(1,5);
        List<ResourceDoc> all = resourceDocMapper.findAll();

        HashMap<Object, Object> allMap = new HashMap<>();
        if(all.isEmpty())
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        else{
            allMap.put("data",all);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }
        return allMap;
    }

    @Override
    public Map<Object, Object> ResourceDocPaging(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ResourceDoc> all = resourceDocMapper.findAll();

        PageInfo pageInfo = new PageInfo<>(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        if(all.isEmpty())
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        else{
            allMap.put("data",all);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            allMap.put("total",total);
        }
        return allMap;
    }

    @Override
    public ResourceDoc findResourceDocById(int id) {
        return resourceDocMapper.findRrsourceDocById(id);
    }
}
