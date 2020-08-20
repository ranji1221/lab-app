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
    private String name;
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

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }

    /*public ConsumeInform(int id, ConsumeInformDto consumeInformDto){
        this.id = id;
        this.name = consumeInformDto.getName();
        this.brand = consumeInformDto.getBrand();
        this.num = consumeInformDto.getNum();
        this.facid = consumeInformDto.getFacid();
        this.factime = DateUtil.DateToString(consumeInformDto.getFactime(),"yyyy-MM-dd");
        this.proid = consumeInformDto.getProid();
        this.supid = consumeInformDto.getSupid();
    }
    public ConsumeInform(ConsumeInformDto consumeInformDto){
        this.name = consumeInformDto.getName();
        this.brand = consumeInformDto.getBrand();
        this.num = consumeInformDto.getNum();
        this.facid = consumeInformDto.getFacid();
        this.factime = DateUtil.DateToString(consumeInformDto.getFactime(),"yyyy-MM-dd");
        this.proid = consumeInformDto.getProid();
        this.supid = consumeInformDto.getSupid();
    }*/
}
