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
    private int deviceModelId;//设备类型id
    @NonNull
    private String uuid;//设备类型uuid唯一标识
    @NonNull
    private String factime;//设备出厂日期
    private String status;

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
