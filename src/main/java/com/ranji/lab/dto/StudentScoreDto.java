package com.ranji.lab.dto;

import lombok.*;

/**
 * 学生成绩实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentScoreDto {
    int id;
    @NonNull
    private int projectId;
    @NonNull
    private int teacherId;
    @NonNull
    private int studentId;
    @NonNull
    private int score;

    private String projectName;
    private String studentName;
    private String teachName;
}
