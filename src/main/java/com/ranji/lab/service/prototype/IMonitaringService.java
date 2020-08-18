package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Monitaring;

import java.util.List;
import java.util.Map;

public interface IMonitaringService {

    int insertMonitaring(Monitaring monitaring);

    Map<Object,Object> findAllMonitaring(int pageNum, int pageSize);

    List<Monitaring> findAllMonitaring();

    Monitaring findById(int id);

    int updateMonitaring(Monitaring monitaring);
}
