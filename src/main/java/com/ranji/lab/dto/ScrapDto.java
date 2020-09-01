package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapDto {
    private int id;
    @NonNull
    private String laboratoryName;
    @NonNull
    private String uuid;
    @NonNull
    private String description;
    @NonNull
    private String date;
    @NonNull
    private String status;
}
