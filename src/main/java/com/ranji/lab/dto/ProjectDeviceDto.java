package com.ranji.lab.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectDeviceDto implements Serializable {
    private int id;
    @NonNull
    private int experimentDeviceId;
    private int projectId;
    @NonNull
    private int deviceNum;
    private String deviceName;
    private Integer status;       //类型，0新增，1修改
}
