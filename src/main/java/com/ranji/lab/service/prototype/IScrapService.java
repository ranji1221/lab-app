package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ScrapDto;
import com.ranji.lab.dto.ScrapInsertDto;
import com.ranji.lab.entity.Scrap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface IScrapService {

    int insertScrap(ScrapInsertDto scrapInsertDto);
    //取消报废
    int updateScrapValue(Scrap scrap);

    int updateScrapStatus(ScrapDto scrap);

    Map<Object, Object> allScrap();

    Map<Object, Object> allScrap(int pageNum, int pageSize);

    //模糊查询
    Map<Object, Object> likeFindAll(int pageNum, int pageSize, String like);

    Map<Object, Object> statusFindScrap(Integer status, int page, int limit);
}
