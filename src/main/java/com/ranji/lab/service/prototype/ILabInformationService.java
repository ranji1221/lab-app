package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.LabInformation;

import java.util.List;
import java.util.Map;

public interface ILabInformationService {
    int insertLabInformation(LabInformation labInformation);

    int updateLabInformation(LabInformation labInformation);

    List<LabInformation> findAllLabInformation();

    Map<Object,Object> findAllLabInformation(int pageNum, int pageSize);

    LabInformation findById(int id);
}
