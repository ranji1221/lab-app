package com.ranji.lab.dto;

import com.ranji.lab.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAndDeviceTypeNameDto {

    private int id;
    @NonNull
    private String deviceName;
    @NonNull
    private String brand;
    @NonNull
    private int count;
    @NonNull
    private String facid;
    @NonNull
    private String factime;
    @NonNull
    private String proid;
    @NonNull
    private String supid;
    @NonNull
    private String type;
    @NonNull
    private String typeName;

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }

}
