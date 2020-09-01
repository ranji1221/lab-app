package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.dto.LaboratoryStatusMonitoringDto;
import com.ranji.lab.dto.StatusMonitoringDto;
import com.ranji.lab.entity.Laboratory;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ILaboratoryService {
    int insertLaboratory(LaboratoryDto laboratoryDto,String devices);

    int updateLaboratory(Laboratory laboratory);

    Map<Object,Object> findAllLaboratory();

    Map<Object,Object> findAllLaboratory(int pageNum,int pageSize);

    List<Laboratory> dateFindAll(String date, String timeStart, String timeStop);

    List<StatusMonitoringDto> laboratoryStatusMonitoring();
    List<StatusMonitoringDto> laboratoryStatusMonitoringAll();

    //模糊查询实验室
    Map<Object,Object> likeFindAll(int pageNum,int pageSize, String like);
    Map<Object,Object> likeFindAll(String like);
}
