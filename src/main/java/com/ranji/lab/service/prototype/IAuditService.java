package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Audit;

import java.util.Map;

public interface IAuditService {

    int insertAudit(Audit audit);

    Map<Object,Object> findAll();
}
