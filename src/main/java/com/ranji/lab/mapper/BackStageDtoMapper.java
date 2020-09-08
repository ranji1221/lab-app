package com.ranji.lab.mapper;

import com.ranji.lab.dto.BackStageDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BackStageDtoMapper {

    @Select("SELECT date_format(t.date, '%Y-%m-%d') date FROM (SELECT CURRENT_DATE + #{dayId} date) t")
    String findNowDays(int dayId);

    @Select("SELECT date_format(t.date, '%H:%i:%s') time FROM (SELECT NOW() date) t")
    String findNowTime();

    @Select("SELECT l.laboratory_name, a.date, count(*) count FROM arrange a LEFT JOIN laboratory l ON l.id = a.laboratory_id WHERE a.date = #{date} GROUP BY laboratory_id\n")
    List<BackStageDto> findNowDaysLaboratory(String date);

    @Select("select count(*) as all_count from laboratory")
    int findAllCount();

    @Select("select count(*) as finished_count from (select count(*) count from arrange where status = 1 and date = #{date} group by laboratory_id) count")
    int findFinishedCount(String date);

    @Select("select count(*) as unfinished_count from (select count(*) count from arrange where status = 0 and date = #{date} group by laboratory_id) count")
    int findUnfinishedCount(String date);

    @Select("select count(*) no_count from laboratory l  where l.id not in ( select a.laboratory_id from arrange a where a.date = #{date} GROUP BY a.laboratory_id )\n")
    int findNoCount(String date);

}
