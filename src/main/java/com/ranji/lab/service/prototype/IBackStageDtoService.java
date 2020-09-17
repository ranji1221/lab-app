package com.ranji.lab.service.prototype;

import java.util.Map;

public interface IBackStageDtoService {

    Map<Object,Object> findSevenDaysAgoData();
    Map<Object,Object> findSevenDaysAgoDataDemo();

    Map<Object,Object> findAllAndFinishedAndUnfinishedAndNoCountData();
    Map<Object,Object> findAllAndFinishedAndUnfinishedAndNoCountDataDemo();
}
