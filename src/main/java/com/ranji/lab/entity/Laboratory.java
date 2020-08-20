package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Laboratory implements Serializable {
    private int id;
    @NonNull
    private String laboratoryName;
    @NonNull
    private String faculty;
    @NonNull
    private String laboratoryResponsibility;
}
