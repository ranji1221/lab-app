package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Regime;
import com.ranji.lab.entity.Study;

import java.util.List;
import java.util.Map;

public interface IStudyService {

    int insertStudy(Study study);

    int updateStudy(Study study);

    Map<Object,Object> findAllStudy();

    Map<Object,Object> findAllStudy(int pageNum, int pageSize);

    Study findById(int id);
}
