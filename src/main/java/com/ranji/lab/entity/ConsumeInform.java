package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 耗材基本资料实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsumeInform implements Serializable {
    private int id;
    @NonNull
    private String name;//耗材名称
    @NonNull
    private String brand;//耗材品牌
    @NonNull
    private int num;//耗材数量
    @NonNull
    private String facid;//出厂编号
    @NonNull
    private String factime;//出厂日期
    @NonNull
    private String proid;//生产厂家编号
    @NonNull
    private String supid;//供应商编号
    @NonNull
    private String unitName;//耗材单位
    @NonNull
    private int type;//耗材类型

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }

}
