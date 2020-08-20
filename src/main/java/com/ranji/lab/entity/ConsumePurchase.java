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
    private int consumeId;
    @NonNull
    private int num;
    @NonNull
    private String date;
    @NonNull
    private String applicant;
    @NonNull
    private String status;

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
