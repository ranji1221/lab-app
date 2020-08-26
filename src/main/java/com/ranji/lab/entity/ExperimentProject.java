package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 实验项目实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ExperimentProject implements Serializable {
    private int id;
    @NonNull
    private String experimentName;//实验项目名字
    @NonNull
    private String experimentTarget;//实验项目目标
    @NonNull
    private String experimentContent;//实验项目内容
    @NonNull
    private String experimentProcess;//实验项目流程
    @NonNull
    private int status;//实验项目
}
