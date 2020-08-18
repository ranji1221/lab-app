package com.ranji.lab.mapper;

import com.ranji.lab.entity.Notice;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class NoticeMapperTests {
    @Resource
    private NoticeMapper noticeMapper;

    @Test
    public void testInsert(){
        String date = "2020-8-12";
        Notice notice = new Notice("高校疫情期间在线教学及国际慕课建设线上研讨会召开",
                "教务处",
                "王军",
                date,
                "为进一步落实教育部与省教育厅相关文件精神，加强疫情期间在线教学及推进国际慕课建设工作，4月29日，由我校教务处、教师促进与教师发展中心主办，学堂在线协办的“山西省高校疫情期间在线教学及国际慕课建设线上研讨会”顺利召开。本次会议的主题是“疫情期间在线教学经验分享及国际慕课建设交流”."
        );
        noticeMapper.insertNotice(notice);
    }
    @Test void testUpdate(){
        String date = "2020-8-11";
        Notice notice = new Notice("高校疫情期间在线教学及国际慕课建设线上研讨会召开",
                "教务处",
                "王均",
                date,
                "为进一步落实教育部与省教育厅相关文件精神，加强疫情期间在线教学及推进国际慕课建设工作，4月29日，由我校教务处、教师促进与教师发展中心主办，学堂在线协办的“山西省高校疫情期间在线教学及国际慕课建设线上研讨会”顺利召开。本次会议的主题是“疫情期间在线教学经验分享及国际慕课建设交流”."
        );
        notice.setId(1);
        noticeMapper.updateNotice(notice);
    }
}
