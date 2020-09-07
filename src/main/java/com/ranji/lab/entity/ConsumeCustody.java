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
    private String recipient;//领用人
    private int arrangeProjectId;
    @NonNull
    private String date;//领用日期
    private int status;//领用状态
    private String unitName;
    private String consumeName;
    private double count;
    private String projectName;

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDate() {
        return DateUtil.StringToDate(this.date, "yyyy-MM-dd");
    }
}
