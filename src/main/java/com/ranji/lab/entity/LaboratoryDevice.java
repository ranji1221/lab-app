package com.ranji.lab.entity;

import lombok.*;

/**
 * 实验室设备关联实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class LaboratoryDevice {
    private int id;
    @NonNull
    private int laboratoryId;//实验室id
    @NonNull
    private int deviceId;//设备id
    @NonNull
    private int status;//实验室状态
}
