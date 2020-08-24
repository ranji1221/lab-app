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
public class Device implements Serializable {
    private int id;
    @NonNull
    private String deviceName;
    @NonNull
    private String brand;
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
    private String unitName;
    @NonNull
    private int lifetime;

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }

    public Device(int id, DeviceDto deviceDto){
        this.id = id;
        this.facid = deviceDto.getFacid();
        this.factime = DateUtil.DateToString(deviceDto.getFactime(),"yyyy-MM-dd");
        this.proid = deviceDto.getProid();
        this.supid = deviceDto.getSupid();
    }
}
