package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.LaboratoryDevice;

import java.util.Map;

public interface ILaboratoryDeviceService {
    int insertLaboratoryDevice(LaboratoryDevice laboratoryDevice);

    int updateLaboratoryDevice(LaboratoryDevice laboratoryDevice);

    Map<Object,Object> allLaboratoryDevice();
}
