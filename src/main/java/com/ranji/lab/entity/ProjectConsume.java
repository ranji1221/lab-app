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
    private int experimentConsumeId;
    @NonNull
    private int projectId;
    @NonNull
    private int consumeNum;
    @NonNull
    private String unitName;
}
