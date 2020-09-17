package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDeviceDto {
    private int id;
    @NonNull
    private int deviceId;

    private int experimentDeviceId;

    private int deviceNum;

}