package com.ranji.lab.mapper;

import com.ranji.lab.entity.StudentScore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentScoreMapper {
    @Insert("insert into student_score (project_id,teacher_id,student_id,score) values (#{projectId},#{teacherId},#{studentId},#{score})")
    int insertStudentScore(StudentScore studentScore);

    @Update("update student_score set project_id = #{projectId} teacher_id = #{teacherId} student_id = #{studentId} score = #{score} where id = #{id}")
    int updateStudentScore(StudentScore studentScore);

    @Select("select * from student_score")
    List<StudentScore> findAll();
}
