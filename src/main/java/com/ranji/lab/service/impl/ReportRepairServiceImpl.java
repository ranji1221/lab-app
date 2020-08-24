package com.ranji.lab.service.impl;

import com.ranji.lab.entity.ReportRepair;
import com.ranji.lab.mapper.ReportRepairMapper;
import com.ranji.lab.service.prototype.IReportRepairService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportRepairServiceImpl implements IReportRepairService {

    @Resource
    private ReportRepairMapper reportRepairMapper;

    @Override
    public int insertReportRepair(ReportRepair reportRepair) {
        return reportRepairMapper.insertReportRepair(reportRepair);
    }

    @Override
    public int updateReportRepair(ReportRepair reportRepair) {
        return reportRepairMapper.updateReportRepair(reportRepair);
    }

    @Override
    public Map<Object, Object> AllReportRepair() {
        List<ReportRepair> reportRepairs = reportRepairMapper.allReportRepair();
        HashMap<Object, Object> reportRepairsMap = new HashMap<>();
        reportRepairsMap.put("data",reportRepairs);
        return reportRepairsMap;
    }
}
