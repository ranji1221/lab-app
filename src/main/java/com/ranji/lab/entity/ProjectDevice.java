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
    private int experimentDeviceId;
    @NonNull
    private int projectId;
    @NonNull
    private int deviceNum;
}
