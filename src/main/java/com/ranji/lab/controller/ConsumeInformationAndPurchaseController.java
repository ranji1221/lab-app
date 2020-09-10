package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ranji.lab.dto.*;
import com.ranji.lab.entity.*;
import com.ranji.lab.service.prototype.*;
import com.ranji.lab.util.DateUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "耗材信息和申请购置接口")
@RestController
public class ConsumeInformationAndPurchaseController {
    @Resource
    private IConsumeInformService iConsumeInformService;

    @Resource
    private IConsumePurchaseService iConsumePurchaseService;

    @Resource
    private IConsumeTypeService iConsumeTypeService;

    /**
     * 插入耗材信息
     * @return
     */
    @ApiOperation(value="插入耗材信息", notes="根据传过来的设备信息来插入耗材信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "耗材名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand", value = "品牌型号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "耗材数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "facid", value = "出厂编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factime", value = "出厂日期(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "proid", value = "生产厂家编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "supid", value = "供应商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "耗材类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "unitName", value = "耗材单位", required = true, dataType = "String")
    })
    @PostMapping(value="/insertconsumeinform",produces = "text/plain;charset=utf-8")
    public String insertConsumeInform(ConsumeInformDto consumeInformDto){
        Map<Object,Object> insertConsumeInformMap = new HashMap<>();
        int i = iConsumeInformService.insertConsumeInform(consumeInformDto);
        if(i<1){
            insertConsumeInformMap.put("status","failure");
            return JSON.toJSONString(insertConsumeInformMap);
        }else{
            insertConsumeInformMap.put("status","success");
            return JSON.toJSONString(insertConsumeInformMap);
        }
    }

    @ApiOperation(value="更新耗材信息", notes="根据传过来的设备信息来更新耗材信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "耗材名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand", value = "品牌型号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "耗材数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "facid", value = "出厂编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factime", value = "出厂日期(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "proid", value = "生产厂家编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "supid", value = "供应商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "耗材类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "耗材id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "unitName", value = "耗材单位", required = true, dataType = "String")
    })
    @PostMapping(value="/updateconsumeinform",produces = "text/plain;charset=utf-8")
    public String updateConsumeInform(ConsumeInform consumeInform){
        Map<Object,Object> updateConsumeInformMap = new HashMap<>();
        int i = iConsumeInformService.updateConsumeInform(consumeInform);
        if(i<1){
            updateConsumeInformMap.put("status","failure");
            return JSON.toJSONString(updateConsumeInformMap);
        }else{
            updateConsumeInformMap.put("status","success");
            return JSON.toJSONString(updateConsumeInformMap);
        }
    }

    /**
     * 获取所有设备信息
     * @return
     */
    @ApiOperation(value="获取所有耗材信息", notes="调用接口直接返回所有耗材信息")
    @GetMapping(value = "/allconsumeinform",produces = "text/plain;charset=utf-8")
    public String findAllConsumeInform(){
        List<ConsumeInform> allConsumeInform = iConsumeInformService.findAllConsumeInform();
        Map<Object,Object> consumeInformMap = new HashMap<>();
        if(!allConsumeInform.isEmpty()){
            consumeInformMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            consumeInformMap.put("data",allConsumeInform);
            return JSON.toJSONString(consumeInformMap);
        }else{
            consumeInformMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(consumeInformMap);
        }
    }
    @ApiOperation(value="分页查询耗材信息", notes="根据传过来的分页信息来查询耗材信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页几条", required = true, dataType = "String")
    })
    @GetMapping(value = "/allconsumeinformpaging",produces = "text/plain;charset=utf-8")
    public String findAllConsumeInformPaging(int pageNum , int pageSize){
        Map<Object,Object> allConsumeInform = iConsumeInformService.findAllConsumeInform(pageNum,pageSize);
        if(!allConsumeInform.isEmpty()){
            allConsumeInform.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeInform);
        }else{
            allConsumeInform.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeInform);
        }
    }
    @ApiOperation(value="分页查询设备信息", notes="根据传过来的信息来查询设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "需要的条数", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allconsumeinforms",produces = "text/plain;charset=utf-8")
    public String findAllConsumeInforms(int page , int limit){
        Map<Object,Object> allConsumeInform = iConsumeInformService.findAllConsumeInform(page,limit);
        if(!allConsumeInform.isEmpty()){
            allConsumeInform.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeInform);
        }else{
            allConsumeInform.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeInform);
        }
    }

    @ApiOperation(value="插入申请购置信息", notes="根据传过来的设备信息来插入申请购置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "consumeId", value = "购置耗材id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "购置数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "购置日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "applicant", value = "购置申请人", required = true, dataType = "String")
    })
    @PostMapping(value = "/insertconsumepurchase",produces = "text/plain;charset=utf-8")
    public String insertConsumePurchase(ConsumePurchase consumePurchase){
        Map<Object,Object> insertConsumePurchaseMap = new HashMap<>();
        int i = iConsumePurchaseService.insertConsumePurchase(consumePurchase);
        if(i<1){
            insertConsumePurchaseMap.put("status","failure");
            return JSON.toJSONString(insertConsumePurchaseMap);
        }else{
            insertConsumePurchaseMap.put("status","success");
            return JSON.toJSONString(insertConsumePurchaseMap);
        }
    }

    @ApiOperation(value = "更新申请购置信息", notes = "根据传过来的设备信息来更新申请购置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购置id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "consumeId", value = "购置耗材id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "购置数量", required = true, dataType = "String")
    })
    @PostMapping(value = "/updateconsumepurchase", produces = "text/plain;charset=utf-8")
    public String updateConsumePurchase(ConsumePurchase consumePurchase, String consumePurchasess) {
        Map<Object, Object> updateConsumePurchaseMap = new HashMap<>();
        int i = 0;
        if (consumePurchase != null) {
            i = iConsumePurchaseService.updateConsumePurchase(consumePurchase);
        }
        if (consumePurchasess != null) {
            ConsumePurchase[] consumePurchases = JSON.parseObject(consumePurchasess, new TypeReference<ConsumePurchase[]>() {
            });
            for (ConsumePurchase consumePurchase1 : consumePurchases) {
                i = iConsumePurchaseService.updateConsumePurchase(consumePurchase1);
            }
        }
        if (i < 1) {
            updateConsumePurchaseMap.put("status", "failure");
            return JSON.toJSONString(updateConsumePurchaseMap);
        } else {
            updateConsumePurchaseMap.put("status", "success");
            return JSON.toJSONString(updateConsumePurchaseMap);
        }

    }

    @ApiOperation(value = "获取所有申请购置信息", notes = "根据传过来的设备信息来获得申请购置信息")
    @GetMapping(value = "/allconsumepurchase",produces = "text/plain;charset=utf-8")
    public String findAllConsumePurchase(){
        List<ConsumePurchase> allConsumePurchase = iConsumePurchaseService.findAllConsumePurchase();
        Map<Object,Object> consumePurchaseMap = new HashMap<>();
        if(!allConsumePurchase.isEmpty()){
            consumePurchaseMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            consumePurchaseMap.put("data",allConsumePurchase);
            return JSON.toJSONString(consumePurchaseMap);
        }else{
            consumePurchaseMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(consumePurchaseMap);
        }
    }
    @ApiOperation(value="获取所有申请购置信息(包含name)", notes="根据传过来的设备信息来获得申请购置信息(包含id)")
    @GetMapping(value = "/allconsumepurchases",produces = "text/plain;charset=utf-8")
    public String findAllConsumePurchases(int page,int limit){
        Map<Object,Object> map = new HashMap<>();
        List<ConsumePurchase> allConsumePurchases = iConsumePurchaseService.findAllConsumePurchases(page, limit);
        /**
         * 通过id查询名字并传至前台
         */
        List<ConsumePurchaseDto> allConsumePurchasess = new ArrayList<>();
            for (ConsumePurchase allConsumePurchase : allConsumePurchases) {
                int id = allConsumePurchase.getId();
                String consumeName = iConsumePurchaseService.findNameById(id).getConsumeName();
                ConsumePurchaseDto consumePurchaseDto = new ConsumePurchaseDto();

                consumePurchaseDto.setApplicant(allConsumePurchase.getApplicant());
                consumePurchaseDto.setConsumeName(consumeName);
                consumePurchaseDto.setConsumeId(allConsumePurchase.getConsumeId());
                consumePurchaseDto.setDate(DateUtil.DateToString(allConsumePurchase.getDate(),"yyyy-MM-dd"));
                consumePurchaseDto.setNum(allConsumePurchase.getNum());
                consumePurchaseDto.setStatus(allConsumePurchase.getStatus());
                consumePurchaseDto.setId(allConsumePurchase.getId());
                consumePurchaseDto.setUnitName(allConsumePurchase.getUnitName());
                allConsumePurchasess.add(consumePurchaseDto);
            }
            int total = iConsumePurchaseService.getCount();
            map.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            map.put("data",allConsumePurchasess);
            map.put("total",total);
            return JSON.toJSONString(map);
    }
    @ApiOperation(value="分页查询申请购置信息", notes="根据传过来的设备信息来获得申请购置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "每页几条", required = true, dataType = "String")

    })
    @GetMapping(value = "/allconsumepurchasepaging",produces = "text/plain;charset=utf-8")
    public String findAllConsumePurchasePaging(int page,int limit){
        Map<Object, Object> consumePurchaseMap = iConsumePurchaseService.findAllConsumePurchase(page, limit);
        consumePurchaseMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        return JSON.toJSONString(consumePurchaseMap);
    }


    @ApiOperation(value="插入耗材类型信息", notes="根据传过来的设备信息来插入耗材类型信息")
    @ApiImplicitParam(name = "typeName", value = "耗材类型名称", required = true, dataType = "String")
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "/insertconsumetype",produces = "text/plain;charset=utf-8")
    public String insertConsumeType(ConsumeTypeDto consumeTypeDto){
        Map<Object,Object> insertConsumeTypeMap = new HashMap<>();
        int i = iConsumeTypeService.insertConsumeType(consumeTypeDto);
        if(i>0){
            insertConsumeTypeMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(insertConsumeTypeMap);
        }else{
            insertConsumeTypeMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(insertConsumeTypeMap);
        }
    }

    @ApiOperation(value="更新耗材类型信息", notes="根据传过来的设备信息来更新耗材类型信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeName", value = "耗材类型名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "耗材类型id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "/updateconsumetype",produces = "text/plain;charset=utf-8")
    public String updateConsumeType(ConsumeType consumeType){
        Map<Object,Object> updateConsumeTypeMap = new HashMap<>();
        int i = iConsumeTypeService.updateConsumeType(consumeType);
        if(i>0){
            updateConsumeTypeMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(updateConsumeTypeMap);
        }else{
            updateConsumeTypeMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(updateConsumeTypeMap);
        }
    }

    @ApiOperation(value="查找所有耗材类型信息", notes="根据传过来的设备信息来查询耗材类型信息")
    @GetMapping(value="allconsumetype",produces = "text/plain;charset=utf-8")
    public String allConsumeType(){
        Map<Object, Object> objectObjectMap = iConsumeTypeService.allConsumeType();
        if(!objectObjectMap.isEmpty()){
            objectObjectMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(objectObjectMap);
        }else{
            objectObjectMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(objectObjectMap);
        }
    }

    @ApiOperation(value="分页查找所有耗材类型信息", notes="分页查找所有耗材类型信息")
    @GetMapping(value="allconsumetypepaging",produces = "text/plain;charset=utf-8")
    public String allConsumeTypePaging(int page,int limit){
        Map<Object, Object> objectObjectMap = iConsumeTypeService.allConsumeType(page,limit);
        return JSON.toJSONString(objectObjectMap);
    }

    @ApiOperation(value="通过id查找所有耗材类型信息", notes="根据传过来的id来查询耗材类型信息")
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @GetMapping(value="allconsumebytype",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String allConsumeByType(int id){
        Map<Object, Object> AllConsumeByTypeIdMap = iConsumeInformService.findAllConfumeInformByTypeId(id);
        if(!AllConsumeByTypeIdMap.isEmpty()){
            AllConsumeByTypeIdMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(AllConsumeByTypeIdMap);
        }else{
            AllConsumeByTypeIdMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(AllConsumeByTypeIdMap);
        }
    }

    @ApiOperation(value="查询所有耗材和耗材类型", notes="查询所有耗材和耗材类型")
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @GetMapping(value = "allconsumeandconsumename",produces = "text/plain;charset=utf-8")
    public String allConsumeAndConsumeName(){
        Map<Object,Object> allConsumeAndConsumeNameMap = new HashMap<>();
        List<ConsumeInformAndConsumeTypeNameDto> consumeAndConsumeName = iConsumeInformService.findConsumeAndConsumeName();
        if(!consumeAndConsumeName.isEmpty()){
            allConsumeAndConsumeNameMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            allConsumeAndConsumeNameMap.put("data",consumeAndConsumeName);
            return JSON.toJSONString(allConsumeAndConsumeNameMap);
        }else{
            allConsumeAndConsumeNameMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeAndConsumeNameMap);
        }
    }
    //分页查询
    @ApiOperation(value="分页查询查询所有耗材和耗材类型", notes="查询所有耗材和耗材类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String")
    })
    @GetMapping(value = "pageFindConsumeAndConsumeName",produces = "text/plain;charset=utf-8")
    public String pageFindConsumeAndConsumeName(int page,int limit){
        Map<Object, Object> objectObjectMap = iConsumeInformService.pageFindConsumeAndConsumeName(page, limit);
        if(!objectObjectMap.isEmpty()){
            objectObjectMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(objectObjectMap);
        }else{
            objectObjectMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(objectObjectMap);
        }
    }



    //按照预约id查询实验耗材
    @ApiOperation(value="按照预约id查询实验耗材", notes="按照预约id查询实验耗材")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "arrangeProjectId", value = "预约id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @GetMapping(value="/arrangeProjectIdFindconsumeInform",produces = "text/plain;charset=utf-8")
    public String arrangeProjectIdFindconsumeInform(int arrangeProjectId){
        List<ConsumeInform> consumeInformList = iConsumeInformService.arrangeProjectIdFindconsumeInform(arrangeProjectId);
        Map<String,Object> map = new HashMap<>();
        map.put("data",consumeInformList);
        return JSON.toJSONString(map);
    }

    //模糊查询耗材信息
   /* @ApiOperation(value="分页模糊查询所有耗材和耗材类型", notes="查询所有耗材和耗材类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    })
    @GetMapping(value = "pageLikeFindConsumeAndConsumeName",produces = "text/plain;charset=utf-8")
    public String pageLikeFindConsumeAndConsumeName(int page,int limit,String like){
        Map<Object, Object> objectObjectMap = iConsumeInformService.pageLikeFindConsumeAndConsumeName(page, limit, like);
        if(!objectObjectMap.isEmpty()){
            objectObjectMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(objectObjectMap);
        }else{
            objectObjectMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(objectObjectMap);
        }
    }*/
    @ApiOperation(value="模糊查询所有耗材和耗材类型", notes="模糊查询所有耗材和耗材类型")
    @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    @GetMapping(value = "pageLikeFindConsumeAndConsumeName",produces = "text/plain;charset=utf-8")
    public String pageLikeFindConsumeAndConsumeName(String like){
        Map<Object, Object> objectObjectMap = iConsumeInformService.pageLikeFindConsumeAndConsumeName(like);
        if(!objectObjectMap.isEmpty()){
            objectObjectMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(objectObjectMap);
        }else{
            objectObjectMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(objectObjectMap);
        }
    }


    //模糊查询购置信息
    /*@ApiOperation(value="分页模糊查询购置信息", notes="分页模糊查询购置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    })
    @GetMapping(value = "likeFindAllConsumePurchase",produces = "text/plain;charset=utf-8")
    public String likeFindAll(int page,int limit,String like){
        Map<Object, Object> objectObjectMap = iConsumePurchaseService.likeFindAll(page, limit, like);
        if(!objectObjectMap.isEmpty()){
            objectObjectMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(objectObjectMap);
        }else{
            objectObjectMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(objectObjectMap);
        }
    }*/
    @ApiOperation(value="模糊查询购置信息", notes="模糊查询购置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    })
    @GetMapping(value = "likeFindAllConsumePurchase", produces = "text/plain;charset=utf-8")
    public String likeFindAll(String like) {
        Map<Object, Object> objectObjectMap = iConsumePurchaseService.likeFindAll(like);
        if (!objectObjectMap.isEmpty()) {
            objectObjectMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(objectObjectMap);
        } else {
            objectObjectMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(objectObjectMap);
        }
    }

    @ApiOperation(value = "按照状态查询所有购置", notes = "按照状态查询所有购置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "String")
    })
    @GetMapping(value = "statusFindAllConsumePurchase", produces = "text/plain;charset=utf-8")
    public String statusFindAll(Integer status, int page, int limit) {
        Map<Object, Object> objectObjectMap = iConsumePurchaseService.statusFindAll(status, page, limit);
        if (!objectObjectMap.isEmpty()) {
            objectObjectMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(objectObjectMap);
        } else {
            objectObjectMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(objectObjectMap);
        }
    }
}
