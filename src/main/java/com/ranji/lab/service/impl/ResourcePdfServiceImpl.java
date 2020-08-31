package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ResourcePdf;
import com.ranji.lab.mapper.ResourcePdfMapper;
import com.ranji.lab.service.prototype.IResourcePdfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourcePdfServiceImpl implements IResourcePdfService {

    @Resource
    private ResourcePdfMapper resourcePdfMapper;

    @Override
    public int insertResourcePdf(ResourcePdf resourcePdf) {
        return resourcePdfMapper.insertResourceDoc(resourcePdf);
    }

    @Override
    public int updateResourcePdf(ResourcePdf resourcePdf) {
        return resourcePdfMapper.updateResourceDoc(resourcePdf);
    }

    @Override
    public Map<Object, Object> ResourcePdfInTen() {
        PageHelper.startPage(1,5);
        List<ResourcePdf> all = resourcePdfMapper.findAll();

        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty())
            allMap.put(Code.FAILURE.getCode(),Code.FAILURE.getMsg());
        else{
            allMap.put("data",all);
            allMap.put(Code.SUCCESS.getCode(),Code.SUCCESS.getMsg());
        }
        return allMap;
    }

    @Override
    public Map<Object, Object> ResourcePdfPaging(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ResourcePdf> all = resourcePdfMapper.findAll();

        PageInfo pageInfo = new PageInfo<>(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        if(all.isEmpty())
            allMap.put(Code.FAILURE.getCode(),Code.FAILURE.getMsg());
        else{
            allMap.put("data",all);
            allMap.put(Code.SUCCESS.getCode(),Code.SUCCESS.getMsg());
            allMap.put("total",total);
        }
        return allMap;
    }

    @Override
    public ResourcePdf findResourcePdfById(int id) {
        return resourcePdfMapper.findResoucePdfById(id);
    }
}
