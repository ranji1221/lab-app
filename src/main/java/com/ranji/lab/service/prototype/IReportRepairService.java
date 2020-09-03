package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ReportRepairDto;
import com.ranji.lab.dto.ReportRepairInsertDto;
import com.ranji.lab.entity.ReportRepair;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface IReportRepairService {

    int insertReportRepair(ReportRepairInsertDto reportRepairInsertDto);

    int updateReportRepair(ReportRepair reportRepair);

    Map<Object,Object> AllReportRepair();

    Map<Object,Object> AllReportRepair(int pageNum,int pageSize);

    /**
     * 模糊查询所有维修设备
     * @return
     */
    Map<Object,Object> likeFinAllReportRepair(String like);
}
