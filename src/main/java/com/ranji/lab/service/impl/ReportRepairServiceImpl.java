package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ReportRepairDto;
import com.ranji.lab.dto.ReportRepairInsertDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.ReportRepair;
import com.ranji.lab.mapper.DeviceMapper;
import com.ranji.lab.mapper.ReportRepairMapper;
import com.ranji.lab.service.prototype.IReportRepairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportRepairServiceImpl implements IReportRepairService {

    @Resource
    private ReportRepairMapper reportRepairMapper;
    @Resource
    private DeviceMapper deviceMapper;

    @Override
    @Transactional
    public int insertReportRepair(ReportRepairInsertDto reportRepairInsertDto) {
        String uuid = reportRepairInsertDto.getUuid();
        ArrayList<String> strings = JSON.parseObject(uuid, new TypeReference<ArrayList<String>>(){});
        for (String s : strings) {
            List<Device> deviceByuuid = deviceMapper.findDeviceByuuid(s);
            if(deviceByuuid.isEmpty()){
                return 0;
            }
        }
        for (String s : strings) {
            int deviceId = deviceMapper.findDeviceIdByuuid(s);
            ReportRepair reportRepair = new ReportRepair();
            reportRepair.setDeviceId(deviceId);
            reportRepair.setDate(reportRepairInsertDto.getDate());
            reportRepair.setDescription(reportRepairInsertDto.getDescription());
            reportRepairMapper.insertReportRepair(reportRepair);
            Device device = new Device();
            device.setId(deviceId);
            device.setStatus(3);
            deviceMapper.updateDevice(device);
        }
        return 1;
    }

    @Override
    @Transactional
    public int updateReportRepair(ReportRepair reportRepair) {
        Device device = new Device();
        device.setId(reportRepair.getDeviceId());
        device.setStatus(0);
        deviceMapper.updateDevice(device);
        return reportRepairMapper.updateReportRepairStatus(reportRepair);
    }

    @Override
    public Map<Object, Object> AllReportRepair() {
        List<ReportRepairDto> reportRepairs = reportRepairMapper.allReportRepair();
        HashMap<Object, Object> reportRepairsMap = new HashMap<>();
        reportRepairsMap.put("data",reportRepairs);
        return reportRepairsMap;
    }

    @Override
    public Map<Object, Object> AllReportRepair(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ReportRepairDto> all = reportRepairMapper.allReportRepair();

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);

        return allMap;
    }

    @Override
    public Map<Object, Object> likeFinAllReportRepair(String like) {
        List<ReportRepairDto> all = reportRepairMapper.likeFinAllReportRepair(like);

        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",all.size());

        return allMap;
    }
}
