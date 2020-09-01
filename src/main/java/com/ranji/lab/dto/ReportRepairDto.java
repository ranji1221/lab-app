package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRepairDto {
    private int id;
    @NonNull
    private int deviceId;
    @NonNull
    private String description;
    @NonNull
    private String date;
    private String status;
    @NonNull
    private String laboratoryName;
    @NonNull
    private String uuid;
}
