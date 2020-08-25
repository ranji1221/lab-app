package com.ranji.lab.entity;

import com.ranji.lab.dto.DeviceDto;
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
@RequiredArgsConstructor
@AllArgsConstructor
public class Device implements Serializable {
    private int id;
    @NonNull
    private int deviceModelId;
    @NonNull
    private String uuid;
    @NonNull
    private String factime;

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }

    public Device(int id, DeviceDto deviceDto){
        this.id = id;
        this.factime = DateUtil.DateToString(deviceDto.getFactime(),"yyyy-MM-dd");
    }
}
