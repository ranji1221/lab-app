package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 耗材使用表
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectConsume implements Serializable {
    private int id;
    @NonNull
    private int experimentConsumeId;//实验耗材id
    @NonNull
    private int projectId;//实验项目id
    @NonNull
    private int consumeNum;//实验所需耗材数量
    @NonNull
    private String unitName;//耗材单位
}
