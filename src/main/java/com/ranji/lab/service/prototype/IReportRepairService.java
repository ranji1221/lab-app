package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.ReportRepair;

import java.util.Map;

public interface IReportRepairService {

    int insertReportRepair(ReportRepair reportRepair);

    int updateReportRepair(ReportRepair reportRepair);

    Map<Object,Object> AllReportRepair();
}
