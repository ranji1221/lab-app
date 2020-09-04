package com.ranji.lab.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {

    private int id;
    @NonNull
    private String name;
    @NonNull
    private int priority;
    @NonNull
    private String access;
    private int pid;
    private List<MenuDto> menu;
}
