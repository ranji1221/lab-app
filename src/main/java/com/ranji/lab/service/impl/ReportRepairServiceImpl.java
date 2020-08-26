package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public Map<Object, Object> AllReportRepair(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ReportRepair> all = reportRepairMapper.allReportRepair();

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);

        return allMap;
    }
}
