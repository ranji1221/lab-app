package com.ranji.lab.entity;

import com.ranji.lab.dto.DeviceDto;
import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备信息实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DeviceModel implements Serializable {
    private int id;
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
