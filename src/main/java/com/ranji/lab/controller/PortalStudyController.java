package com.ranji.lab.controller;


import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Study;
import com.ranji.lab.service.prototype.IStudyService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "门户教学科研接口")
@RestController
public class PortalStudyController {

    @Resource
    private IStudyService iStudyService;

    /*
      通过前台表单的数据插入教学科研
    */
    @ApiOperation(value="插入教学科研", notes="根据传过来的教学科研信息来插入教学科研详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源(教务处)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "发布时间(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "/insertstudy",produces = "text/plain;charset=utf-8")
    public String insertStudy(Study study){
        Map<Object,Object> insertStudyMap = new HashMap<>();
        int i = iStudyService.insertStudy(study);
        if(i<1){
            insertStudyMap.put("status","failure");
            return JSON.toJSONString(insertStudyMap);
        }else{
            insertStudyMap.put("status","success");
            return JSON.toJSONString(insertStudyMap);
        }
    }
    /*
      通过前台表单的数据更新教学科研
    */
    @ApiOperation(value="更新教学科研", notes="根据传过来的教学科研信息来更新教学科研详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源(教务处)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "更新时间(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "/updatestudy",produces = "text/plain;charset=utf-8")
    public String updateStudy(Study study){
        Map<Object,Object> updateStudyMap = new HashMap<>();
        int i = iStudyService.insertStudy(study);
        if(i<1){
            updateStudyMap.put("status","failure");
            return JSON.toJSONString(updateStudyMap);
        }else{
            updateStudyMap.put("status","success");
            return JSON.toJSONString(updateStudyMap);
        }

    }
    /*
    前台通过请求获得经由时间排序的教学科研
     */
    @ApiOperation(value="查询所有的教学科研", notes="前端通过访问接口获得所需教学科研")
    @GetMapping(value = "/allstudy",produces = "text/plain;charset=utf-8")
    public String allStudy(){
        Map<Object,Object> studyMap = iStudyService.findAllStudy();
        if(!studyMap.isEmpty()) {
            studyMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(studyMap);
        }else{
            studyMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(studyMap);
        }
    }
    /*
    前台通过请求获得经由时间排序以及前五条的教学科研
     */
    @ApiOperation(value="查询前五条的教学科研", notes="前端通过访问接口获得所需教学科研")
    @GetMapping(value = "/allstudylatestfive",produces = "text/plain;charset=utf-8")
    public String allStudyLimitFive(){
        Map<Object,Object> allStudyeOnPaging = iStudyService.findAllStudy(1,5);
        if(!allStudyeOnPaging.isEmpty()) {
            allStudyeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allStudyeOnPaging);
        }else{
            allStudyeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allStudyeOnPaging);
        }
    }

    /*
    前台通过请求获得经由时间排序以及分页后的教学科研
     */
    @ApiOperation(value="分页查询教学科研", notes="前端通过访问接口获得所需教学科研")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allstudypaging",produces = "text/plain;charset=utf-8")
    public String allStudyOnPaging(int pageNum,int pageSize){
        Map<Object,Object> allStudyeOnPaging = iStudyService.findAllStudy(pageNum,pageSize);
        if(!allStudyeOnPaging.isEmpty()) {
            allStudyeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allStudyeOnPaging);
        }else{
            allStudyeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allStudyeOnPaging);
        }
    }

    @ApiOperation(value="分页查询所有教学科研", notes="前端通过访问接口获得所需教学科研")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "需要几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allstudys",produces = "text/plain;charset=utf-8")
    public String allStudys(int page , int limit){
        Map<Object,Object> studyMap = iStudyService.findAllStudy(page,limit);
        if(!studyMap.isEmpty()) {
            studyMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(studyMap);
        }else{
            studyMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(studyMap);
        }
    }


    @ApiOperation(value="查询由前五条的教学科研", notes="前端通过访问接口获得所需教学科研")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    @PostMapping(value = "/findstudybyid/{id}",produces = "text/plain;charset=utf-8")
    public String findStudyById(@PathVariable("id") int id){
        Study studyById = iStudyService.findById(id);
        Map<Object,Object> study = new HashMap<>();
        study.put("data",studyById);
        study.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(study);
    }

    /*
        查询连续两条的教学科研
    */
    @ApiOperation(value="查询由时间排序的连续两条的教学科研", notes="前端通过访问接口获得所需教学科研")
    @ApiImplicitParam(name = "studyOffsetId", value = "第几篇", required = true, dataType = "String")
    @GetMapping(value = "/findstudynexttonext",produces = "text/plain;charset=utf-8")
    public String findStudyNextToNext(int studyOffsetId){
        Map<Object, Object> studyNextToNext = iStudyService.findStudyNextToNext(studyOffsetId);
        if(!studyNextToNext.isEmpty()){
            studyNextToNext.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(studyNextToNext);
        }else {
            studyNextToNext.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(studyNextToNext);
        }
    }


    @ApiOperation(value="模糊查找教学科研", notes="模糊查找教学科研")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "like", value = "模糊词", required = true, dataType = "String")
    })
    @GetMapping(value = "/likestudy",produces = "text/plain;charset=utf-8")
    public String findLikeStudy(String like){
        Map<Object, Object> likeStudy = iStudyService.findLikeStudy(like);
        if(!likeStudy.isEmpty()){
            likeStudy.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else{
            likeStudy.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        }
        return JSON.toJSONString(likeStudy);
    }
}
