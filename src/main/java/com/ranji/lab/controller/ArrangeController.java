package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.entity.Arrange;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Laboratory;
import com.ranji.lab.service.prototype.IArrangeService;
import com.ranji.lab.service.prototype.ILaboratoryService;
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
    @Resource
    private ILaboratoryService laboratoryService;
    /**
     * 添加实验项目信息
     * @return
     */
    @ApiOperation(value="插入预约信息", notes="根据传过来的信息来插入预约项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "laboratoryId", value = "实验室id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "实验项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "实验人数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "预约日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStop", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "responsibility", value = "管理员", required = true, dataType = "String"),
            @ApiImplicitParam(name = "devices", value = "设备信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "consumes", value = "耗材信息", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "insertArrange")
    @ResponseBody
    public String insertArrange1(Arrange arrange,String devices,String consumes){
        Map<Object,Object> insertNewsMap = new HashMap<>();
        int i = iArrangeService.insertArrange(arrange,devices,consumes);
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
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String")
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
            @ApiImplicitParam(name = "laboratoryId", value = "实验室id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectId", value = "实验项目id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "实验人数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "预约日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStop", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "responsibility", value = "管理员", required = true, dataType = "String"),
            @ApiImplicitParam(name = "devices", value = "设备信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "consumes", value = "耗材信息", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "updArrange")
    @ResponseBody
    public String updArrange(Arrange arrange,String devices, String consumes){
        Map<Object,Object> insertNewsMap = new HashMap<>();
        int i = iArrangeService.updArrange(arrange,devices,consumes);
        if(i<1){
            insertNewsMap.put("status","failure");
            return JSON.toJSONString(insertNewsMap);
        }else{
            insertNewsMap.put("status","success");
            return JSON.toJSONString(insertNewsMap);
        }
    }
    /**
     * @param id
     * @return
     */
    @ApiOperation(value="按照id查询的预约信息", notes="按照id查询的预约信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "预约接口", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @GetMapping(value = "/idFindArrange",produces = "text/plain;charset=utf-8")
    public String idFindArrange(int id){
        ArrangeDto arrangeDto = iArrangeService.idFindArrange(id);
        Map<Object,Object> newsMap = new HashMap<>();
        if(arrangeDto!=null){
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            newsMap.put("data", arrangeDto);
        }
        return JSON.toJSONString(newsMap);
    }

    //按照所选时间查询可预约的所有实验室
    @ApiOperation(value="按照所选时间查询可预约的所有实验室", notes="按照所选时间查询可预约的所有实验室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "预约实验日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timeStop", value = "结束时间", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @GetMapping(value = "/dateFindAll",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String dateFindAll(String date,String timeStart,String timeStop){
        List<Laboratory> laboratories = laboratoryService.dateFindAll(date, timeStart, timeStop);
        Map<Object,Object> newsMap = new HashMap<>();
        if(laboratories!=null){
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            newsMap.put("data", laboratories);
        }
        return JSON.toJSONString(newsMap);
    }

    //分页模糊查询
    @ApiOperation(value="分页模糊查询预约实验项目", notes="分页模糊查询预约实验项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    })
    @GetMapping(value = "/pageFindlikeFindArrange",produces = "text/plain;charset=utf-8")
    public Object pageFindlikeFindArrange(int page,int limit,String like){
        Map<Object, Object> pageExperimentProject = iArrangeService.pageFindlikeFindArrange(page,limit,like);
        if(!pageExperimentProject.isEmpty()) {
            pageExperimentProject.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(pageExperimentProject);
        }else{
            pageExperimentProject.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(pageExperimentProject);
        }
    }
}
