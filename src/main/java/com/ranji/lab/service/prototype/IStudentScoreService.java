package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.StudentScore;

import java.util.Map;

public interface IStudentScoreService {
    int insertStudentScore(StudentScore studentScore);

    int updateStudentScore(StudentScore studentScore);

    Map<Object,Object> findAll();

    Map<Object,Object> findAll(int pageNum, int pageSize);
}
