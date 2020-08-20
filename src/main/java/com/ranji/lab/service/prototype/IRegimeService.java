package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Notice;
import com.ranji.lab.entity.Regime;

import java.util.List;
import java.util.Map;

public interface IRegimeService {
    int insertRegime(Regime regime);

    int updateRegime(Regime regime);

    Map<Object,Object> findAllRegime();

    Map<Object,Object> findAllRegime(int pageNum, int pageSize);

    Regime findById(int id);

    Map<Object,Object> findRegimeNextToNext(int regimeId);
}
