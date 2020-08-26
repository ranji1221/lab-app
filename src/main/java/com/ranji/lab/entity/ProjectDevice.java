package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 设备使用表
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectDevice implements Serializable {
    private int id;
    @NonNull
    private int experimentDeviceId;//实验设备id
    @NonNull
    private int projectId;//实验项目id
    @NonNull
    private int deviceNum;//实验所需设备数量
}
