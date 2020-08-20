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
    private int num;
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

    public Device(int id, String deviceName, String brand, String conid, int num, String roomnames, String facid, String factime, String proid, String supid) {
    }

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }

    public Device(int id, DeviceDto deviceDto){
        this.id = id;
        this.num = deviceDto.getId();
        this.facid = deviceDto.getFacid();
        this.factime = DateUtil.DateToString(deviceDto.getFactime(),"yyyy-MM-dd");
        this.proid = deviceDto.getProid();
        this.supid = deviceDto.getSupid();
    }
}
