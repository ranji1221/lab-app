package com.ranji.lab.mapper;

import com.ranji.lab.dto.StudentScoreDto;
import com.ranji.lab.entity.StudentScore;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentScoreMapper {
    @Insert("insert into student_score (project_id,teacher_id,student_id,score) values (#{projectId},#{teacherId},#{studentId},#{score})")
    int insertStudentScore(StudentScore studentScore);

    @Update("update student_score set score = #{score} where id = #{id}")
    int updateStudentScore(StudentScore studentScore);

    @Select("select ss.*,ep.experiment_name projectName,tu.`name` studentName,tut.`name` teachName from student_score ss join t_user tu on ss.student_id = tu.id join arrange a on ss.project_id = a.id join experiment_project ep on a.project_id = ep.id join t_user tut on ss.teacher_id = tut.id ")
    List<StudentScoreDto> findAll();
}
