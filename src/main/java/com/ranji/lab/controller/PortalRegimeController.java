package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.NewsDto;
import com.ranji.lab.entity.*;
import com.ranji.lab.service.prototype.*;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "门户实验制度接口")
@RestController
public class PortalRegimeController {


    @Resource
    private IRegimeService iRegimeService;
    @Resource
    private IBannerService iBannerService;

    @ApiOperation(value="测试swagger", notes="测试swagger")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"),
    })
    @PostMapping(value = "/testSwagger")
    @ResponseBody
    public String testSwagger(){
        return "successful";
    }



      /*通过前台表单的数据插入实验制度*/
    @ApiOperation(value="插入实验制度", notes="根据传过来的实验制度信息来插入实验制度详细信息")
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
    @PostMapping(value = "/insertregime",produces = "text/plain;charset=utf-8")
    public String insertRegime(Regime regime){
        Map<Object,Object> insertRegimeMap = new HashMap<>();
        int i = iRegimeService.insertRegime(regime);
        if(i<1){
            insertRegimeMap.put("status","failure");
            return JSON.toJSONString(insertRegimeMap);
        }else{
            insertRegimeMap.put("status","success");
            return JSON.toJSONString(insertRegimeMap);
        }

    }


      /*通过前台表单的数据更新实验制度*/
      @ApiOperation(value="更新实验制度", notes="根据传过来的实验制度信息来更新实验制度详细信息")
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
    @PostMapping(value = "/updateregime",produces = "text/plain;charset=utf-8")
    public String updateRegime(Regime regime){
        Map<Object,Object> insertRegimeMap = new HashMap<>();
        int i = iRegimeService.updateRegime(regime);
        if(i<1){
            insertRegimeMap.put("status","failure");
            return JSON.toJSONString(insertRegimeMap);
        }else{
            insertRegimeMap.put("status","success");
            return JSON.toJSONString(insertRegimeMap);
        }

    }
    /*
    前台通过请求获得经由时间排序的实验制度
     */
    @ApiOperation(value="查询所有由时间排序以及分页后的实验制度", notes="前端通过访问接口获得所有实验制度")
    @GetMapping(value = "/allregime",produces = "text/plain;charset=utf-8")
    public String allRegime(){
        Map<Object,Object> regimeMap = iRegimeService.findAllRegime();
        if(!regimeMap.isEmpty()) {
            regimeMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(regimeMap);
        }else {
            regimeMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(regimeMap);
        }
    }

    /*
    前台通过请求获得经由时间排序以及分前五的实验制度
     */
    @ApiOperation(value="查询前五条的实验制度", notes="前端通过访问接口获得所需实验制度")
    @GetMapping(value = "/allregimelatestfive",produces = "text/plain;charset=utf-8")
    public String allRegimeLimitFive() {
        Map<Object, Object> allRegimeOnPaging = iRegimeService.findAllRegime(1, 5);
        if (!allRegimeOnPaging.isEmpty()) {
            allRegimeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allRegimeOnPaging);
        }else {
            allRegimeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allRegimeOnPaging);
        }
    }

    /*
    前台通过请求获得经由时间排序以及分页后的实验制度
     */
    @ApiOperation(value="分页查询实验制度", notes="前端通过访问接口获得所需实验制度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allregimepaging",produces = "text/plain;charset=utf-8")
    public String allRegimeOnPaging(int pageNum,int pageSize) {
        Map<Object, Object> allRegimeOnPaging = iRegimeService.findAllRegime(pageNum, pageSize);
        if (!allRegimeOnPaging.isEmpty()) {
            allRegimeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allRegimeOnPaging);
        }else {
            allRegimeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allRegimeOnPaging);
        }
    }

    @ApiOperation(value="分页查询所有实验制度", notes="前端通过访问接口获得所需实验制度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "需要几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allregimes",produces = "text/plain;charset=utf-8")
    public String allRegimes(int page , int limit){
        Map<Object,Object> regimeMap = iRegimeService.findAllRegime(page,limit);
        if(!regimeMap.isEmpty()) {
            regimeMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(regimeMap);
        }else{
            regimeMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(regimeMap);
        }
    }


    @ApiOperation(value="通过id查询实验制度", notes="前端通过访问接口获得所需实验制度")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    @PostMapping(value = "/findregimebyid/{id}",produces = "text/plain;charset=utf-8")
    public String findRegimeById(@PathVariable("id") int id){
        Regime regimeById = iRegimeService.findById(id);
        Map<Object,Object> regime = new HashMap<>();
        regime.put("data",regimeById);
        regime.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(regime);
    }

    /*
    查询连续两条的实验制度
    */
    @ApiOperation(value="查询由时间排序的连续两条的实验制度", notes="前端通过访问接口获得所需实验制度")
    @ApiImplicitParam(name = "regimeOffsetId", value = "第几篇", required = true, dataType = "String")
    @GetMapping(value = "/findregimenexttonext",produces = "text/plain;charset=utf-8")
    public String findRegimeNextToNext(int regimeOffsetId){
        Map<Object, Object> regimeNextToNext = iRegimeService.findRegimeNextToNext(regimeOffsetId);
        if(!regimeNextToNext.isEmpty()){
            regimeNextToNext.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(regimeNextToNext);
        }else {
            regimeNextToNext.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(regimeNextToNext);
        }
    }

    @ApiOperation(value="模糊查找实验制度", notes="模糊查找实验制度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "like", value = "模糊词", required = true, dataType = "String")
    })
    @GetMapping(value = "/likeregime",produces = "text/plain;charset=utf-8")
    public String findLikeRegime(String like){
        Map<Object, Object> likeRegime = iRegimeService.findLikeRegime(like);
        if(!likeRegime.isEmpty()){
            likeRegime.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else{
            likeRegime.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        }
        return JSON.toJSONString(likeRegime);
    }

}
