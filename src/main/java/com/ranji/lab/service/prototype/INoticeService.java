package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.News;
import com.ranji.lab.entity.Notice;

import java.util.List;
import java.util.Map;

public interface INoticeService {

    int insertNotice(Notice notice);

    int updateNotice(Notice notice);

    Map<Object,Object> findAllNotice();

    Map<Object,Object> findAllNotice(int pageNum, int pageSize);

    Notice findById(int id);

    Map<Object,Object> findNoticeNextToNext(int noticeId);
}
