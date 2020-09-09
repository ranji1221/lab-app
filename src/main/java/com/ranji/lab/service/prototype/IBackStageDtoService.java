package com.ranji.lab.service.prototype;

import java.util.Map;

public interface IBackStageDtoService {

    Map<Object,Object> findSevenDaysAgoData();

    Map<Object,Object> findAllAndFinishedAndUnfinishedAndNoCountData();
}
