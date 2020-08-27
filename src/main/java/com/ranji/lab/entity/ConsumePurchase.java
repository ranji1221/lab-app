package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请购置实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsumePurchase implements Serializable {
    private int id;
    @NonNull
    private int consumeId;//购置耗材id
    @NonNull
    private int num;//购置耗材数量
    @NonNull
    private String date;//购置耗材日期
    @NonNull
    private String applicant;//购置耗材申请人
    @NonNull
    private String status;//购置耗材状态
    @NonNull
    private String unitName;
    @NonNull
    private String consumeName;

    public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }

    /*public ConsumePurchase(int id , ConsumePurchaseDto consumePurchaseDto){
        this.id = id;
        this.consumeId = consumePurchaseDto.getConsumeId();
        this.num = consumePurchaseDto.getNum();
        this.date = DateUtil.DateToString(consumePurchaseDto.getDate(),"yyyy-MM-dd");
        this.applicant = consumePurchaseDto.getApplicant();
    }
    public ConsumePurchase(ConsumePurchaseDto consumePurchaseDto){
        this.consumeId = consumePurchaseDto.getConsumeId();
        this.num = consumePurchaseDto.getNum();
        this.date = DateUtil.DateToString(consumePurchaseDto.getDate(),"yyyy-MM-dd");
        this.applicant = consumePurchaseDto.getApplicant();
    }*/
}
