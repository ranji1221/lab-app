package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ranji.lab.dto.ProjectDeviceDto;
import com.ranji.lab.dto.ScrapDto;
import com.ranji.lab.dto.ScrapInsertDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Scrap;
import com.ranji.lab.service.prototype.IScrapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "设备报废接口")
@RestController
public class ScrapController {

    @Resource
    private IScrapService iScrapService;

    /**
     * 第一次报修(需要上级审核)
     * @param scrapInsertDto
     * @return
     */
    @ApiOperation(value="插入所需报废的设备", notes="插入所需报废的值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "所需报废设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(2报废中，4报废)", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "报废描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "修改日期(xxxx-xx-xx)", required = true, dataType = "String")
    })
    @PostMapping(value = "insertscrap", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "teacher", "laboratoryMgr", "manager"}, logical = Logical.OR)
    public String insertScrap(ScrapInsertDto scrapInsertDto){
        HashMap<Object, Object> insertScrapMap = new HashMap<>();
        int i = iScrapService.insertScrap(scrapInsertDto);
        if(i<1){
            insertScrapMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(insertScrapMap);
        }else{
            insertScrapMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(insertScrapMap);
        }
    }

    @ApiOperation(value="取消报废)", notes="取消报废")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "所需报废设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(2报废中，4报废)",dataType = "String"),
            @ApiImplicitParam(name = "description", value = "报废描述", required = true , dataType = "String"),
            @ApiImplicitParam(name = "date", value = "修改日期(xxxx-xx-xx)", required = true , dataType = "String"),
            @ApiImplicitParam(name = "id", value = "报废编号", required = true , dataType = "String")
    })
    @PostMapping(value = "/updatescrapvalue", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "teacher", "laboratoryMgr", "manager"}, logical = Logical.OR)
    public String updateScrap(Scrap scrap){
        HashMap<Object, Object> insertScrapMap = new HashMap<>();
        int i = iScrapService.updateScrapValue(scrap);
        if(i<1){
            insertScrapMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(insertScrapMap);
        }else{
            insertScrapMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(insertScrapMap);
        }
    }

    @ApiOperation(value = "更改所需报废的设备状态", notes = "更改所需报废的值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "所需报废设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(2报废中，4报废)", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "报废描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "修改日期(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "报废编号", required = true, dataType = "String")
    })
    @PostMapping(value = "/updatescrapstatus", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "laboratoryMgr", "manager"}, logical = Logical.OR)
    public String updateScrapStatus(ScrapDto scrap, String scrapss) {
        HashMap<Object, Object> insertScrapMap = new HashMap<>();
        int i = 0;
        if (scrap != null) {
            i = iScrapService.updateScrapStatus(scrap);
        }
        if (scrapss != null) {
            ScrapDto[] scraps = JSON.parseObject(scrapss, new TypeReference<ScrapDto[]>() {
            });
            for (ScrapDto scrap1 : scraps) {
                i = iScrapService.updateScrapStatus(scrap1);
            }
        }
        if (i < 1) {
            insertScrapMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(insertScrapMap);
        } else {
            insertScrapMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(insertScrapMap);
        }
    }

    @ApiOperation(value="查询所有设备报废", notes="查询所有设备报废")
    @GetMapping(value = "/allscrap",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllScrap(){
        Map<Object, Object> allMap = iScrapService.allScrap();
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value = "分页查询所有设备报废", notes = "查询所有设备报废")
    @GetMapping(value = "/allscrappaging", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllScrapPaging(int page, int limit) {
        Map<Object, Object> allMap = iScrapService.allScrap(page, limit);
        return JSON.toJSONString(allMap);
    }

    //按照状态查询
    @ApiOperation(value = "按照状态查询设备报废", notes = "按照状态查询设备报废")
    @GetMapping(value = "/statusFindScrap", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String statusFindScrap(Integer status, int page, int limit) {
        Map<Object, Object> allMap = iScrapService.statusFindScrap(status, page, limit);
        return JSON.toJSONString(allMap);
    }

    /**
     * 模糊查询
     *
     * @param like
     * @return
     */
    @ApiOperation(value = "模糊查询所有设备报废", notes = "模糊查询所有设备报废")
    @GetMapping(value = "/likeFindAll", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String likeFindAll(String like) {
        Map<Object, Object> allMap = iScrapService.likeFindAll(like);
        return JSON.toJSONString(allMap);
    }
}
