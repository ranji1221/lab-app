package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.ResourceDoc;
import com.ranji.lab.service.prototype.IResourceDocService;
import com.ranji.lab.service.prototype.IResourcePdfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Api(tags = "教学资源查询接口")
@RestController
public class ResourceController {

    @Resource
    private IResourcePdfService iResourcePdfService;
    @Resource
    private IResourceDocService iResourceDocService;

    @ApiOperation(value = "分页查询教学资源Doc",notes = "分页查询教学资源")
    @GetMapping(value = "/allresourcedocpaging",produces = "text/plain;charset=utf-8")
    public String allResourceDocPaging(int page,int limit){
        Map<Object, Object> allMap = iResourceDocService.ResourceDocPaging(page, limit);
        return JSON.toJSONString(allMap);
    }
    @ApiOperation(value = "分页查询教学资源Pdf",notes = "分页查询教学资源")
    @GetMapping(value = "/allresourcepdfpaging",produces = "text/plain;charset=utf-8")
    public String allResourcePdfPaging(int page,int limit){
        Map<Object, Object> allMap = iResourcePdfService.ResourcePdfPaging(page, limit);
        return JSON.toJSONString(allMap);
    }
}
