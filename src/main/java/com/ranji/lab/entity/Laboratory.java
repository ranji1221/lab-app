package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 实验室实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Laboratory implements Serializable {
    private int id;
    @NonNull
    private String laboratoryName;//实验室名字
    @NonNull
    private String faculty;//实验室所属系别
    @NonNull
    private String laboratoryResponsibility;//实验室负责人
}
