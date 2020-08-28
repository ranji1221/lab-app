package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.dto.LaboratoryStatusMonitoringDto;
import com.ranji.lab.dto.StatusMonitoringDto;
import com.ranji.lab.entity.Laboratory;

import java.util.List;
import java.util.Map;

public interface ILaboratoryService {
    int insertLaboratory(LaboratoryDto laboratoryDto);

    int updateLaboratory(Laboratory laboratory);

    Map<Object,Object> findAllLaboratory();

    Map<Object,Object> findAllLaboratory(int pageNum,int pageSize);

    List<Laboratory> dateFindAll(String date, String timeStart, String timeStop);

    List<StatusMonitoringDto> laboratoryStatusMonitoring();
}
