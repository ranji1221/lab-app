package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Regime;
import com.ranji.lab.entity.Study;
import com.ranji.lab.mapper.StudyMapper;
import com.ranji.lab.service.prototype.IStudyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudyServiceImpl implements IStudyService {
    @Resource
    private StudyMapper studyMapper;

    @Override
    public int insertStudy(Study study) {
        return studyMapper.insertStudy(study);
    }

    @Override
    public int updateStudy(Study study) {
        return studyMapper.updateStudy(study);
    }

    @Override
    @Transactional
    public Map<Object,Object> findAllStudy() {
        List<Study> all = studyMapper.findAll();
        int total = studyMapper.count();
        Map<Object,Object> allStudy = new HashMap<>();
        allStudy.put("data",all);
        allStudy.put("total",total);
        return allStudy;
    }

    @Override
    public Map<Object,Object> findAllStudy(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Study> allStudy = studyMapper.findAll();

        PageInfo<Study> allStudyPaging = new PageInfo<>(allStudy);
        long total = allStudyPaging.getTotal();

        Map<Object,Object> allStudyOnPaging = new HashMap<>();
        allStudyOnPaging.put("data",allStudy);
        allStudyOnPaging.put("total",total);
        return allStudyOnPaging;
    }

    @Override
    public Study findById(int id) {
        return studyMapper.findById(id);
    }

    @Override
    public Map<Object, Object> findStudyNextToNext(int studyId) {
        List<Study> studyNextToNext = studyMapper.findStudyNextToNext(studyId);
        HashMap<Object, Object> studyNextToNextMap = new HashMap<>();
        studyNextToNextMap.put("data",studyNextToNext);
        return studyNextToNextMap;
    }

    @Override
    public Map<Object, Object> findLikeStudy(String like, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Study> all = studyMapper.findLikeStudy(like);

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        Map<Object,Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);

        return allMap;
    }
    @Override
    public Map<Object, Object> findLikeStudy(String like) {
        List<Study> all = studyMapper.findLikeStudy(like);
        Map<Object,Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",all.size());

        return allMap;
    }
}
