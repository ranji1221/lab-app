package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.StudentScoreDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.StudentScore;
import com.ranji.lab.mapper.StudentScoreMapper;
import com.ranji.lab.service.prototype.IStudentScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentScoreServiceImpl implements IStudentScoreService {

    @Resource
    private StudentScoreMapper studentScoreMapper;

    @Override
    public int insertStudentScore(StudentScore studentScore) {
        return studentScoreMapper.insertStudentScore(studentScore);
    }

    @Override
    public int updateStudentScore(StudentScore studentScore) {
        return studentScoreMapper.updateStudentScore(studentScore);
    }

    @Override
    public Map<Object,Object> findAll() {
        List<StudentScoreDto> all = studentScoreMapper.findAll();
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data", all);
        allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        return allMap;
    }

    @Override
    public Map<Object, Object> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentScoreDto> all = studentScoreMapper.findAll();

        PageInfo pageInfo = new PageInfo();
        long total = pageInfo.getTotal();
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data", all);
        allMap.put("total", total);
        allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        return allMap;
    }
}
