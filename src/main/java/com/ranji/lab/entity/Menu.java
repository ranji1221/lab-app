package com.ranji.lab.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Menu {

    private int id;
    @NonNull
    private String name;
    @NonNull
    private int pid;
    @NonNull
    private int priority;
}
