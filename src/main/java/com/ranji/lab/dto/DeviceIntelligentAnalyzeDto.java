package com.ranji.lab.dto;

import com.ranji.lab.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceIntelligentAnalyzeDto {
    private int id;
    private String deviceName;
    private String brand;
    private int status;
    private int count;
    private String laboratoryName;
}
