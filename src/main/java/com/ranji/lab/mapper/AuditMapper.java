package com.ranji.lab.mapper;

import com.ranji.lab.entity.Audit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuditMapper {
    @Insert("insert into audit (username,ip_addr,time,access_addr) values (#{username},#{ipAddr},#{time},#{accessAddr})")
    int insertAudit(Audit audit);

    @Select("select * from audit")
    List<Audit> findAll();
}
