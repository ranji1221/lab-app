package com.ranji.lab.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectDeviceDto {
    private int id;
    @NonNull
    private int experimentDeviceId;
    @NonNull
    private int projectId;
    @NonNull
    private int deviceNum;
    private String deviceName;
    private Integer status;       //类型，0新增，1修改
    private String unitName;
    private int deviceModelId;
}
