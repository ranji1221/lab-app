package com.ranji.lab.dto;

import com.ranji.lab.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto implements Serializable {
    private int id;
    private String factime;
    private int deviceModelId;
    private String uuid;
    private String deviceName;
    private int deviceId;
    private int status;


    public DeviceDto(int id, String deviceName, String brand, String conid, int num, String roomnames, String facid, String factime, String proid, String supid) {
    }

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }
}
