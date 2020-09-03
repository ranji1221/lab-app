package com.ranji.lab.service.impl;

import com.ranji.lab.entity.StudyImage;
import com.ranji.lab.mapper.StudyImageMapper;
import com.ranji.lab.service.prototype.IStudyImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudyImageServiceImpl implements IStudyImageService {

    @Resource
    private StudyImageMapper studyImageMapper;

    @Override
    public int insertStudyImage(StudyImage studyImage) {
        studyImageMapper.insertStudyImage(studyImage);
        return studyImage.getId();
    }

    @Override
    public StudyImage findStudyImageById(int id) {
        return studyImageMapper.findStudyImageById(id);
    }
}
