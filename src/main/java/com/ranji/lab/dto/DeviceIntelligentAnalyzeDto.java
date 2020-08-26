package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
