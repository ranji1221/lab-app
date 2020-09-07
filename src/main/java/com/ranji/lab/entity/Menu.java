package com.ranji.lab.entity;

import lombok.*;

/**
 * 菜单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Menu implements Comparable<Menu>{

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
    public int compareTo(Menu o) {
        if(this.priority-o.priority<0)
            return -1;
        else if(this.priority-o.priority>0)
            return 1;
        else
            return 0;
    }
}
