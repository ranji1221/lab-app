package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.entity.Arrange;
import com.ranji.lab.entity.Code;
import com.ranji.lab.service.prototype.IArrangeService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "预约接口")
@RestController
public class ArrangeController {

    @Resource
    private IArrangeService iArrangeService;
    /**
     * 添加实验项目信息
     * @return
     */
    @ApiOperation(value="插入预约信息", notes="根据传过来的信息来插入预约项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "laboratoryId", value = "实验室id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "实验项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "实验人数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "arrangeTime", value = "当前时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "预约日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStop", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "responsibility", value = "管理员", required = true, dataType = "String"),
            @ApiImplicitParam(name = "devices", value = "设备信息", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "insertArrange")
    @ResponseBody
    public String insertArrange(Arrange arrange,String devices){
        Map<Object,Object> insertNewsMap = new HashMap<>();
        int i = iArrangeService.insertArrange(arrange,devices);
        if(i<1){
            insertNewsMap.put("status","failure");
            return JSON.toJSONString(insertNewsMap);
        }else{
            insertNewsMap.put("status","success");
            return JSON.toJSONString(insertNewsMap);
        }
    }

    /**
     * 查询所有预约信息
     * @return
     */
    @ApiOperation(value="查询所有预约", notes="前端通过访问接口获得所有预约信息")
    @GetMapping(value = "/findAllArrange",produces = "text/plain;charset=utf-8")
    public String findAllArrange(Integer status){
        List<ArrangeDto> allArrange = iArrangeService.findAllArrange(status);
        Map<Object,Object> newsMap = new HashMap<>();
        if(!allArrange.isEmpty()) {
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            newsMap.put("data", allArrange);
            return JSON.toJSONString(newsMap);
        }else{
            newsMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsMap);
        }
    }

    /**
     * 分页查询所有预约实验项目
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value="分页查询预约实验项目", notes="根据传过来的信息来查询实验项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
    })
    @GetMapping(value = "/pageFindAllArrange",produces = "text/plain;charset=utf-8")
    public Object pageFindAllArrange(int page,int limit,Integer status){
        Map<Object, Object> pageExperimentProject = iArrangeService.pageFindAllArrange(page,limit,status);
        if(!pageExperimentProject.isEmpty()) {
            pageExperimentProject.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(pageExperimentProject);
        }else{
            pageExperimentProject.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(pageExperimentProject);
        }
    }
    /**


    /**
     * 修改预约信息
     * @return
     */
    @ApiOperation(value="修改预约信息", notes="根据传过来的信息来插入预约信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "预约id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "laboratoryId", value = "实验室id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "实验项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "实验人数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "arrangeTime", value = "当前时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "预约日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStop", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "responsibility", value = "管理员", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "预约状态", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "updArrange")
    @ResponseBody
    public String updArrange(Arrange arrange){
        Map<Object,Object> insertNewsMap = new HashMap<>();
        int i = iArrangeService.updArrange(arrange);
        if(i<1){
            insertNewsMap.put("status","failure");
            return JSON.toJSONString(insertNewsMap);
        }else{
            insertNewsMap.put("status","success");
            return JSON.toJSONString(insertNewsMap);
        }
    }

    //验证是否可以预约
    @ApiOperation(value="验证是否可以预约", notes="根据传过来的信息验证是否可以预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "laboratoryId", value = "实验室id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "预约实验日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStop", value = "结束时间", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "yesOrNoArrange")
    @ResponseBody
    public String yesOrNoArrange(Arrange arrange){
        List<ArrangeDto> arrangeDtos = iArrangeService.yesOrNoArrange(arrange);
        if(arrangeDtos.size()>0){
            return "no";
        }
        return "ok";
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation(value="按照id查询的预约信息", notes="前端通过访问接口获得预约信息")
    @GetMapping(value = "/idFindArrange",produces = "text/plain;charset=utf-8")
    public String abc(int id){
        ArrangeDto arrangeDto = iArrangeService.idFindArrange(id);
        Map<Object,Object> newsMap = new HashMap<>();
        if(arrangeDto!=null){
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            newsMap.put("data", arrangeDto);
        }
        return JSON.toJSONString(newsMap);
    }
}
