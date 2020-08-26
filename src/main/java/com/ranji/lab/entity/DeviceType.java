package com.ranji.lab.entity;

import lombok.*;

/**
 * 设备类型实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class DeviceType {
    private int id;
    @NonNull
    private String typeName;//设备类型名字
}
