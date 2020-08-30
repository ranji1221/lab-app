package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.News;
import com.ranji.lab.entity.Notice;
import com.ranji.lab.mapper.NoticeMapper;
import com.ranji.lab.service.prototype.INoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements INoticeService {

    @Resource
    private NoticeMapper noticeMapper;
    @Override
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    @Transactional
    public Map<Object,Object> findAllNotice() {
        List<Notice> all = noticeMapper.findAll();
        int total = noticeMapper.count();
        Map<Object,Object> allNotice = new HashMap<>();
        allNotice.put("total",total);
        allNotice.put("data",all);
        return allNotice;
    }

    @Override
    public Map<Object,Object> findAllNotice(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> allNotice = noticeMapper.findAll();
        PageInfo<Notice> allNoticePaging = new PageInfo<>(allNotice);
        long total = allNoticePaging.getTotal();
        Map<Object,Object> allNoticeOnPaging = new HashMap<>();
        allNoticeOnPaging.put("data",allNotice);
        allNoticeOnPaging.put("total",total);
        return allNoticeOnPaging;
    }

    @Override
    public Notice findById(int id) {
        return noticeMapper.findById(id);
    }

    @Override
    public Map<Object, Object> findNoticeNextToNext(int noticeId) {
        List<News> noticeNextToNext = noticeMapper.findNoticeNextToNext(noticeId);
        Map<Object,Object> noticeNextToNextMap = new HashMap<>();
        noticeNextToNextMap.put("data",noticeNextToNext);
        return noticeNextToNextMap;
    }

    @Override
    public Map<Object, Object> findLikeNotice(String like, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Notice> all = noticeMapper.findLikeNotice(like);

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        Map<Object,Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);

        return allMap;
    }
}
