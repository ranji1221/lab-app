package com.ranji.lab.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Menu implements Comparable{

    private int id;
    @NonNull
    private String name;
    @NonNull
    private int pid;
    @NonNull
    private int priority;
    @NonNull
    private String access;

    @Override
    public int compareTo(Object o) {
        Menu menu = (Menu)o;
        if(this.priority-menu.priority<0)
            return -1;
        else if(this.priority-menu.priority>0)
            return 1;
        else
            return 0;
    }
}
