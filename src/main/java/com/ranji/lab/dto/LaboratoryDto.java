package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDto {
    private int id;
    @NonNull
    private String laboratoryName;   //
    @NonNull
    private String faculty;
    @NonNull
    private String laboratoryResponsibility;
    @NonNull
    private int imgSrc;
}
