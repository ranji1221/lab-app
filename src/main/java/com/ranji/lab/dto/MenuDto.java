package com.ranji.lab.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {

    private int id;
    @NonNull
    private String name;
    @NonNull
    private int priority;
}
