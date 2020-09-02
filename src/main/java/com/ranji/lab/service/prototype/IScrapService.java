package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ScrapInsertDto;
import com.ranji.lab.entity.Scrap;

import java.util.Map;

public interface IScrapService {

    int insertScrap(ScrapInsertDto scrapInsertDto);
    //取消报废
    int updateScrapValue(Scrap scrap);

    int updateScrapStatus(Scrap scrap);

    Map<Object,Object> allScrap();

    Map<Object,Object> allScrap(int pageNum , int pageSize);
}
