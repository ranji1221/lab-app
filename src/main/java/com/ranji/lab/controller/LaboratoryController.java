package com.ranji.lab.controller;


import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Laboratory;
import com.ranji.lab.service.prototype.ILaboratoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "实验室接口")
@Controller
public class LaboratoryController {

    @Resource
    private ILaboratoryService iLaboratoryService;

    @ApiOperation(value="插入实验室", notes="根据传过来的信息插入实验室")
    @PostMapping(value = "insertlaboratory",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String insertLaboratory(LaboratoryDto laboratoryDto, @RequestParam("file") MultipartFile[] files, String devices){
        Map laboratoryMap = new HashMap<>();
        //-- 1. 获取项目的根目录
        String rootDirectory = System.getProperty("user.dir");
        //-- 2. 创建存放上传资源的目录
        File resourceDirectory = new File(rootDirectory+File.separator+"upload"+File.separator+"image");
        if(!resourceDirectory.exists()) resourceDirectory.mkdirs();
        for(MultipartFile file : files){
            String jpgname = file.getOriginalFilename();
            if(jpgname.substring(jpgname.indexOf(".")+1,jpgname.length()).equals("jpg")) {
                String path = resourceDirectory.getAbsolutePath() + File.separator + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path));
                    laboratoryDto.setImgSrc(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                return "{status:jpg plz}";
            }
        }
        int i = iLaboratoryService.insertLaboratory(laboratoryDto,devices);
        if(i<1){
            laboratoryMap.put("status","failure");
            return JSON.toJSONString(laboratoryMap);
        }else{
            laboratoryMap.put("status","success");
            return JSON.toJSONString(laboratoryMap);
        }
    }

    @ApiOperation(value="更新实验室", notes="根据传过来的信息更新实验室")
    @PostMapping(value = "updatelaboratory",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updateLaboratory(Laboratory laboratory){
        Map laboratoryMap = new HashMap<>();
        int i = iLaboratoryService.updateLaboratory(laboratory);
        if(i<1){
            laboratoryMap.put("status","failure");
            return JSON.toJSONString(laboratoryMap);
        }else{
            laboratoryMap.put("status","success");
            return JSON.toJSONString(laboratoryMap);
        }
    }

    @ApiOperation(value="查询所有实验室", notes="查询所有实验室")
    @GetMapping(value = "alllaboratory",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllLaboratory(){
        Map<Object, Object> allLaboratory = iLaboratoryService.findAllLaboratory();
        if(!allLaboratory.isEmpty()){
            allLaboratory.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allLaboratory);
        }else{
            allLaboratory.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allLaboratory);
        }
    }

    @ApiOperation(value="分页查询所有实验室", notes="分页查询所有实验室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String")
    })
    @GetMapping(value = "alllaboratorypaging",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllLaboratorypaging(int page,int limit){
        Map<Object, Object> allLaboratory = iLaboratoryService.findAllLaboratory(page,limit);
        if(!allLaboratory.isEmpty()){
            allLaboratory.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allLaboratory);
        }else{
            allLaboratory.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allLaboratory);
        }
    }

    //分页模糊查询实验室
    /*@ApiOperation(value="分页模糊查询所有实验室", notes="分页模糊查询所有实验室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    })
    @GetMapping(value = "likeFindAllLaboratory",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String likeFindAll(int page,int limit,String like){
        Map<Object, Object> allLaboratory = iLaboratoryService.likeFindAll(page,limit,like);
        if(!allLaboratory.isEmpty()){
            allLaboratory.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allLaboratory);
        }else{
            allLaboratory.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allLaboratory);
        }
    }*/

    @ApiOperation(value="模糊查询所有实验室", notes="分页模糊查询所有实验室")
    @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    @GetMapping(value = "likeFindAllLaboratory",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String likeFindAll(String like){
        Map<Object, Object> allLaboratory = iLaboratoryService.likeFindAll(like);
        if(!allLaboratory.isEmpty()){
            allLaboratory.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allLaboratory);
        }else{
            allLaboratory.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allLaboratory);
        }
    }
}
