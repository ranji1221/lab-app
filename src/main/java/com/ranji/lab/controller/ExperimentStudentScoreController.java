package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.StudentScoreDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.StudentScore;
import com.ranji.lab.service.prototype.IStudentScoreService;
import com.ranji.lab.service.prototype.IUserService;
import io.swagger.annotations.*;
import lombok.val;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "学生成绩接口")
@RestController
public class ExperimentStudentScoreController {
    @Resource
    private IStudentScoreService iStudentScoreService;
    @Resource
    private IUserService iUserService;

    @ApiOperation(value = "插入学生成绩", notes = "插入学生成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "老师id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "studentId", value = "学生id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "实验项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "score", value = "成绩", required = true, dataType = "String"),
    })
    @PostMapping(value = "/insertstudentscore", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "teacher"}, logical = Logical.OR)
    public String insertStudentScore(StudentScoreDto studentScoreDto){
        StudentScore studentScore = new StudentScore();
        studentScore.setProjectId(studentScoreDto.getProjectId());
        studentScore.setScore(studentScoreDto.getScore());
        studentScore.setStudentId(studentScoreDto.getStudentId());
        studentScore.setTeacherId(studentScoreDto.getTeacherId());
        int i = iStudentScoreService.insertStudentScore(studentScore);
        HashMap<Object, Object> allMap = new HashMap<>();
        if (i < 1)
            allMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
        else
            allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());

        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value="查询所有学生成绩", notes="查询所有学生成绩")
    @GetMapping(value = "/allstudentscore",produces = "text/plain;charset=utf-8")
    public String allStudentScore(){
        Map<Object, Object> all = iStudentScoreService.findAll();
        return JSON.toJSONString(all);
    }

    @ApiOperation(value = "分页查询学生成绩", notes = "查询学生成绩")
    @GetMapping(value = "/allstudentscorepaging", produces = "text/plain;charset=utf-8")
    public String allStudentScorePaging(int page, int limit) {
        Map<Object, Object> all = iStudentScoreService.findAll(page, limit);
        return JSON.toJSONString(all);
    }

    @ApiOperation(value = "修改学生成绩", notes = "修改学生成绩")
    @GetMapping(value = "/updStudentScore", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "teacher"}, logical = Logical.OR)
    public String updStudentScore(StudentScore studentScore) {
        val i = iStudentScoreService.updateStudentScore(studentScore);
        Map<String, Object> map = new HashMap<>();
        if (i > 0) {
            map.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        } else {
            map.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
        }
        return JSON.toJSONString(map);
    }
}
