package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumePurchaseDto {
    private int id;
    private int consumeId;
    private int num;
    private String date;
    private String applicant;
    private String status;
    private String consumeName;
    private String unitName;
}
