package com.ranji.lab.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.LaboratoryDeviceDto;
import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.dto.StatusMonitoringDto;
import com.ranji.lab.entity.Laboratory;
import com.ranji.lab.entity.LaboratoryDevice;
import com.ranji.lab.entity.LaboratoryImage;
import com.ranji.lab.mapper.LaboratoryDeviceMapper;
import com.ranji.lab.mapper.LaboratoryImageMapper;
import com.ranji.lab.mapper.LaboratoryMapper;
import com.ranji.lab.service.prototype.ILaboratoryImageService;
import com.ranji.lab.service.prototype.ILaboratoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaboratoryImageServiceImpl implements ILaboratoryImageService {

    @Resource
    private LaboratoryImageMapper laboratoryImageMapper;

    @Override
    public int insertLaboratoryImage(LaboratoryImage laboratoryImage) {
        laboratoryImageMapper.insertLaboratoryImage(laboratoryImage);
        return laboratoryImage.getId();
    }

    @Override
    public Laboratory findlaboratoryImageById(int id) {
        return laboratoryImageMapper.findlaboratoryImageById(id);
    }
}
