package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 设备类型实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DeviceModel implements Serializable {
    private int id;
    @NonNull
    private String deviceName;//耗材名字
    @NonNull
    private String brand;//耗材型号
    @NonNull
    private String facid;//耗材出厂编号
    @NonNull
    private String proid;//耗材生厂厂家编号
    @NonNull
    private String supid;//设备供应商编号
    @NonNull
    private String type;//设备类型
    @NonNull
    private String unitName;//设备单位
    @NonNull
    private int lifetime;//设备使用时长
    @NonNull
    private int count;//设备总数

}
