package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.StudyImage;

public interface IStudyImageService {
    int insertStudyImage(StudyImage studyImage);

    StudyImage findStudyImageById(int id);
}
