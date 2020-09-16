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

    //查询全部实验室
    @Select("select count(*) as all_count from laboratory")
    int findAllCount();

    //今天已用过的实验室 = 正在使用的实验室数量 + 已完成的实验室数量
    @Select("select count(*) finished_count from (select a.laboratory_id from arrange a where a.date = #{date} and status != 0 GROUP BY a.laboratory_id) count")
    int findUseingCount(String date);

    //今天尚未使用的实验室数量
    @Select("select count(*) from (select a.laboratory_id from arrange a where a.date = #{date} and status = 0 GROUP BY a.laboratory_id) count")
    int findNoUseCount(String date);

    //今天要用到的实验室数量
    @Select("select count(*) from (select a.laboratory_id from arrange a where a.date = #{date} GROUP BY a.laboratory_id) count")
    int findWillUseCount(String date);
}
