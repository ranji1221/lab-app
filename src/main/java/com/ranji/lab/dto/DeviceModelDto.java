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
public class DeviceModelDto implements Serializable {
    private int id;
    @NonNull
    private String deviceName;
    @NonNull
    private String brand;
    @NonNull
    private int num;
    @NonNull
    private String facid;
    @NonNull
    private String factime;
    @NonNull
    private String proid;
    @NonNull
    private int lifetime;
    @NonNull
    private String supid;
    @NonNull
    private String type;
    @NonNull
    private String unitName;


    public DeviceModelDto(int id, String deviceName, String brand, String conid, int num, String roomnames, String facid, String factime, String proid, String supid) {
    }

    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }

    public void setFactime(String factime){
        this.factime=factime;
    }
}
