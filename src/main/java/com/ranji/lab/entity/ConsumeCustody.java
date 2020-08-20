package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 保管领用
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsumeCustody implements Serializable {
    private int id;
    @NonNull
    private int consumeId;
    @NonNull
    private String recipient;
    @NonNull
    private String date;
    @NonNull
    private int count;
    @NonNull
    private String status;

    public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }

    /*public ConsumeCustody(int id, ConsumeCustodyDto consumeCustodyDto){
        this.id = id;
        this.consumeId = consumeCustodyDto.getConsumeId();
        this.recipient = consumeCustodyDto.getRecipient();
        this.date = DateUtil.DateToString(consumeCustodyDto.getDate(),"yyyy-MM-dd");
        this.count = consumeCustodyDto.getCount();
    }
    public ConsumeCustody(ConsumeCustodyDto consumeCustodyDto){
        this.consumeId = consumeCustodyDto.getConsumeId();
        this.recipient = consumeCustodyDto.getRecipient();
        this.date = DateUtil.DateToString(consumeCustodyDto.getDate(),"yyyy-MM-dd");
        this.count = consumeCustodyDto.getCount();
    }*/
}
