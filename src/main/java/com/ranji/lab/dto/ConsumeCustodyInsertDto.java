package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeCustodyInsertDto {
    @NonNull
    private int arrangeProjectId;//项目id
    @NonNull
    private String recipient;//领用人
    @NonNull
    private String Date;
}
