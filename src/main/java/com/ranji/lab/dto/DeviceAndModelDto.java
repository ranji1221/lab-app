package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAndModelDto {
    @NonNull
    private String factime;
    @NonNull
    private String deviceName;
    @NonNull
    private String brand;
    @NonNull
    private String facid;
    @NonNull
    private String proid;
    @NonNull
    private String supid;
    @NonNull
    private String type;
    @NonNull
    private String unitName;
    @NonNull
    private int lifetime;
    @NonNull
    private int count;
}
