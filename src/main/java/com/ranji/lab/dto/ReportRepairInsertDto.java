package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRepairInsertDto {
    private String laboratory;

    private String[] uuid;

    private String description;

    private String Date;
}
