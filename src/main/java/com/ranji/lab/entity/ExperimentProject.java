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
    private String experimentName;
    @NonNull
    private String experimentTarget;
    @NonNull
    private String experimentContent;
    @NonNull
    private String experimentProcess;
    @NonNull
    private String experimentInstrument;
    @NonNull
    private String experimentReagent;
}
