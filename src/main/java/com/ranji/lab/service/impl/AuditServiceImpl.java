package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Audit;
import com.ranji.lab.entity.Code;
import com.ranji.lab.mapper.AuditMapper;
import com.ranji.lab.service.prototype.IAuditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuditServiceImpl implements IAuditService {

    @Resource
    private AuditMapper auditMapper;
    @Override
    public int insertAudit(Audit audit) {
        return auditMapper.insertAudit(audit);
    }

    @Override
    public Map<Object,Object> findAll() {
        List<Audit> all = auditMapper.findAll();
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        allMap.put("data", all);
        return allMap;
    }

    @Override
    public Map<Object, Object> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Audit> all = auditMapper.findAll();

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data", all);
        allMap.put("total", total);
        allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        return allMap;
    }
}
