package com.ranji.lab.dto;

import com.ranji.lab.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDeviceNumDto implements Serializable {
    private int id;
    @NonNull
    private String deviceName;
    @NonNull
    private int count;
}
