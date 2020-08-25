package com.ranji.lab.dto;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * 设备信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto implements Serializable {
    private int id;
    @NonNull
    private String factime;
    @NonNull
    private int deviceModelId;
    @NonNull
    private String uuid;


    public DeviceDto(int id, String deviceName, String brand, String conid, int num, String roomnames, String facid, String factime, String proid, String supid) {
    }

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }
}
