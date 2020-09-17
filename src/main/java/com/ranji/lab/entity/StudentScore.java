package com.ranji.lab.entity;

import lombok.*;

/**
 * 学生成绩实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentScore {
    int id;
    @NonNull
    private int projectId;
    @NonNull
    private int teacherId;
    @NonNull
    private int studentId;
    @NonNull
    private double score;
}
