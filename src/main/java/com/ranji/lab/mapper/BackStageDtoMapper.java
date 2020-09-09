package com.ranji.lab.mapper;

import com.ranji.lab.dto.BackStageDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BackStageDtoMapper {

    @Select("select DATE_FORMAT(DATE_SUB(NOW(), INTERVAL #{dayId} DAY),'%Y-%m-%d') date")
    String findNowDays(int dayId);

    @Select("SELECT date_format(t.date, '%H:%i:%s') time FROM (SELECT NOW() date) t")
    String findNowTime();

    /**
     * 根据日期查找今天将要进行的项目数
     * @param date
     * @return
     */
    @Select("select count(*) from arrange a where a.date=#{date}")
    int findArrangeNum(String date);

    /**
     * 根据日期查找今天预约的项目数
     * @param date
     * @return
     */
    @Select("select count(*) from arrange a where a.arrange_time like '%${date}%'")
    int findArrangeDateNum(String date);

    /**
     * 根据日期查找预约了几种项目
     * @param date
     * @return
     */
    @Select("select count(*) from (select count(*) from arrange a where a.date=#{date} GROUP BY a.project_id) count")
    int findArrangeProjectNum(String date);

    @Select("select count(*) as all_count from laboratory")
    int findAllCount();

    @Select("select count(*) as finished_count from (select count(*) count from arrange where status = 1 and date = #{date} group by laboratory_id) count")
    int findFinishedCount(String date);

    @Select("select count(*) as unfinished_count from (select count(*) count from arrange where status = 0 and date = #{date} group by laboratory_id) count")
    int findUnfinishedCount(String date);

    @Select("select count(*) no_count from laboratory l  where l.id not in ( select a.laboratory_id from arrange a where a.date = #{date} GROUP BY a.laboratory_id )\n")
    int findNoCount(String date);

}
