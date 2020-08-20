package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Notice;
import com.ranji.lab.mapper.NoticeMapper;
import com.ranji.lab.service.prototype.INoticeService;
import org.springframework.stereotype.Service;

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
    public List<Notice> findAllNotice() {
        return noticeMapper.findAll();
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
}
