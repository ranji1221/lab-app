package com.ranji.lab.mapper;

import com.ranji.lab.dto.BackStageDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BackStageDtoMapper {

    @Select("SELECT date_format(t.date, '%Y-%m-%d') date FROM (SELECT CURRENT_DATE + #{dayId} date) t")
    String findNowDays(int dayId);

    @Select("SELECT l.laboratory_name, a.date, count(*) count FROM arrange a LEFT JOIN laboratory l ON l.id = a.laboratory_id WHERE a.date = #{date} GROUP BY laboratory_id\n")
    List<BackStageDto> findNowDaysLaboratory(String date);
}
