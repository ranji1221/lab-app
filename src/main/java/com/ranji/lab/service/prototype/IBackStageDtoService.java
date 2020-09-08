package com.ranji.lab.service.prototype;

import java.util.Map;

public interface IBackStageDtoService {
    Map<Object,Object> findNowAndLatestSevenDaysData();


    Map<Object,Object> findAllAndFinishedAndUnfinishedAndNoCountData();
}
