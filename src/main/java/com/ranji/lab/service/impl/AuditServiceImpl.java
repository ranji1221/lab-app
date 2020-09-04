package com.ranji.lab.service.impl;

import com.ranji.lab.entity.Audit;
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
        allMap.put("data",all);
        return allMap;
    }
}
