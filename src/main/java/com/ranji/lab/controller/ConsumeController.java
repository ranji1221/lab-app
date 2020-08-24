package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.*;
import com.ranji.lab.entity.*;
import com.ranji.lab.service.prototype.*;
import com.ranji.lab.util.DateUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "耗材接口")
@RestController
public class ConsumeController {
    @Resource
    private IConsumeInformService iConsumeInformService;

    @Resource
    private IConsumeCustodyService iConsumeCustodyService;

    @Resource
    private IConsumePurchaseService iConsumePurchaseService;

    @Resource
    private IConsumeNormService iConsumeNormService;

    @Resource
    private IConsumeNormContentService iConsumeNormContentService;

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
    @ApiOperation(value="获取分页后的耗材信息", notes="根据传过来的分页信息来查询耗材信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页几条", required = true, dataType = "String")
    })
    @GetMapping(value = "/allconsumeinform/pagenum/pagesize",produces = "text/plain;charset=utf-8")
    public String findAllConsumeInform(@PathVariable("pagenum") int pageNum , @PathVariable("pagesize") int pageSize){
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

    @ApiOperation(value="插入耗材信息", notes="根据传过来的设备信息来插入耗材信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "consumeId", value = "购置耗材id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "购置数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "购置日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "applicant", value = "购置申请人", required = true, dataType = "String"),
    })
    @PostMapping(value = "/insertconsumepurchase",produces = "text/plain;chartset=utf-8")
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
    @ApiOperation(value="更新耗材信息", notes="根据传过来的设备信息来更新耗材信息")
    @PostMapping(value = "/updateconsumepurchase",produces = "text/plain;chartset=utf-8")
    public String updateConsumePurchase(ConsumePurchase consumePurchase){
        Map<Object,Object> updateConsumePurchaseMap = new HashMap<>();
        int i = iConsumePurchaseService.updateConsumePurchase(consumePurchase);
        if(i<1){
            updateConsumePurchaseMap.put("status","failure");
            return JSON.toJSONString(updateConsumePurchaseMap);
        }else{
            updateConsumePurchaseMap.put("status","success");
            return JSON.toJSONString(updateConsumePurchaseMap);
        }
    }
    @ApiOperation(value="获取所有申请购置信息", notes="根据传过来的设备信息来获得申请购置信息")
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
        if(!allConsumePurchases.isEmpty()){

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

                allConsumePurchasess.add(consumePurchaseDto);
            }
            int total = iConsumePurchaseService.getCount();
            map.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            map.put("data",allConsumePurchasess);
            map.put("total",total);
            return JSON.toJSONString(map);
        }else{
            map.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(map);
        }
    }
    @ApiOperation(value="分页获取申请购置信息", notes="根据传过来的设备信息来获得申请购置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页几条", required = true, dataType = "String")

    })
    @GetMapping(value = "/allconsumepurchase/pagenum/pagesize",produces = "text/plain;charset=utf-8")
    public String findAllConsumePurchase(@PathVariable("pagenum") int pageNum,@PathVariable("pagesize") int pageSize){
        Map<Object,Object> consumePurchaseMap = iConsumePurchaseService.findAllConsumePurchase(pageNum,pageSize);
        if(!consumePurchaseMap.isEmpty()){
            consumePurchaseMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(consumePurchaseMap);
        }else{
            consumePurchaseMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(consumePurchaseMap);
        }
    }

    @ApiOperation(value="插入管理标准", notes="根据传过来的设备信息来插入管理标准")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "添加日期", required = true, dataType = "String"),
    })*/
    @PostMapping(value = "/insertconsumenorm",produces = "text/plain;chartset=utf-8")
    public String insertConsumeNorm(ConsumeNormDto consumeNormDto){
        Map<Object, Object> insertConsumeNormMap = new HashMap<>();
        int i = iConsumeNormService.insertConsumeNorm(consumeNormDto);
        if(i<1){
            insertConsumeNormMap.put("status","failure");
            return JSON.toJSONString(insertConsumeNormMap);
        }else{
            insertConsumeNormMap.put("status","success");
            return JSON.toJSONString(insertConsumeNormMap);
        }
    }
    @ApiOperation(value="更新管理标准", notes="根据传过来的设备信息来更新管理标准")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "添加日期", required = true, dataType = "String"),
    })*/
    @PostMapping(value = "/updateconsumenorm",produces = "text/plain;chartset=utf-8")
    public String updateConsumeNorm(ConsumeNorm consumeNorm){
        Map<Object, Object> updateConsumeNormMap = new HashMap<>();
        int i = iConsumeNormService.updateConsumeNorm(consumeNorm);
        if(i<1){
            updateConsumeNormMap.put("status","failure");
            return JSON.toJSONString(updateConsumeNormMap);
        }else{
            updateConsumeNormMap.put("status","success");
            return JSON.toJSONString(updateConsumeNormMap);
        }
    }

    /*@ApiOperation(value="所有的管理标准", notes="根据传过来的设备信息来获取管理标准")
    @GetMapping(value = "/allconsumenorm",produces = "text/plain;charset=utf-8")
    public String findAllConsumeNorm(){
        List<ConsumeNorm> allConsumeNorm = iConsumeNormService.findAll();
        Map<Object,Object> allConsumeNormPaging = new HashMap<>();
        if(!allConsumeNorm.isEmpty()) {
            allConsumeNormPaging.put("data", allConsumeNorm);
            allConsumeNormPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeNormPaging);
        }else{
            allConsumeNormPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeNormPaging);
        }
    }*/
    @ApiOperation(value="分页查找管理标准", notes="根据传过来的设备信息来获取管理标准")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页几条", required = true, dataType = "String")
    })
    @GetMapping(value = "/allconsumenorm/pagenum/pagesize",produces = "text/plain;charset=utf-8")
    public String findAllConsumeNorm(@PathVariable("pagenum") int pageNum,@PathVariable("pagesize") int pageSize){
        Map<Object,Object> allConsumeNormMap = iConsumeNormService.findAll(pageNum,pageSize);
        if(!allConsumeNormMap.isEmpty()){
            allConsumeNormMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeNormMap);
        }else{
            allConsumeNormMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeNormMap);
        }
    }
    @ApiOperation(value="所有的管理标准及内容", notes="根据传过来的设备信息来获取管理标准及内容")
    @GetMapping(value="/allconsumenormcontent",produces = "text/plain;charset=utf-8")
    public String findAllConsumeNormContent(){
        Map<Object, Object> consumeNormContentMap = iConsumeNormContentService.allConsumeContent();
        return JSON.toJSONString(consumeNormContentMap);
    }

    @ApiOperation(value="插入保管领用", notes="根据传过来的设备信息来插入保管领用")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "添加日期", required = true, dataType = "String"),
    })*/
    @PostMapping(value = "insertConsumeCustody",produces = "text/plain;chartset=utf-8")
    public String insertConsumeCustody(ConsumeCustody consumeCustody){
        Map<Object,Object> insertConsumeCustodyMap = new HashMap<>();
        int i = iConsumeCustodyService.insertConsumeCustody(consumeCustody);
        if(i<1){
            insertConsumeCustodyMap.put("status","failure");
            return JSON.toJSONString(insertConsumeCustodyMap);
        }else{
            insertConsumeCustodyMap.put("status","success");
            return JSON.toJSONString(insertConsumeCustodyMap);
        }

    }
    @ApiOperation(value="更新保管领用", notes="根据传过来的设备信息来更新保管领用")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "添加日期", required = true, dataType = "String"),
    })*/
    @PostMapping(value = "updateConsumeCustody",produces = "text/plain;chartset=utf-8")
    public String updateConsumeCustody(ConsumeCustody consumeCustody){
        Map<Object,Object> updateConsumeCustodyMap = new HashMap<>();
        int i = iConsumeCustodyService.updateConsumeCustody(consumeCustody);
        if(i<1){
            updateConsumeCustodyMap.put("status","failure");
            return JSON.toJSONString(updateConsumeCustodyMap);
        }else{
            updateConsumeCustodyMap.put("status","success");
            return JSON.toJSONString(updateConsumeCustodyMap);
        }

    }
    @GetMapping(value = "/allconsumecustody",produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustody(){
        List<ConsumeCustody> allConsumeCustody = iConsumeCustodyService.findAll();
        Map<Object,Object> allConsumeCustodyPaging = new HashMap<>();
        if(!allConsumeCustody.isEmpty()){
            allConsumeCustodyPaging.put("data",allConsumeCustody);
            allConsumeCustodyPaging.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeCustodyPaging);
        }else{
            allConsumeCustodyPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeCustodyPaging);
        }
    }
    @ApiOperation(value="获得保管领用包括ID", notes="根据传过来的设备信息来获得")
    @GetMapping(value = "/allconsumecustodys",produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustodys(int page,int limit){
        Map<Object,Object> map = new HashMap<>();
        List<ConsumeCustody> allConsumeCustodys = iConsumeCustodyService.findAllConsumeCustodys(page, limit);
            /**
             * 通过id查询名字，并将名字传递至前台
             */
        List<ConsumeCustodyDto> allConsumeCustodyss = new ArrayList<>();
        if(!allConsumeCustodys.isEmpty()){

            for (ConsumeCustody allConsumecustody : allConsumeCustodys) {
                int id = allConsumecustody.getId();
                String name = iConsumeCustodyService.findNameById(id).getConsumeName();

                ConsumeCustodyDto consumeCustodyDto = new ConsumeCustodyDto();

                consumeCustodyDto.setRecipient(allConsumecustody.getRecipient());
                consumeCustodyDto.setConsumeName(name);
                consumeCustodyDto.setConsumeId(allConsumecustody.getConsumeId());
                consumeCustodyDto.setDate(DateUtil.DateToString(allConsumecustody.getDate(),"yyyy-MM-dd"));
                consumeCustodyDto.setStatus(allConsumecustody.getStatus());
                consumeCustodyDto.setId(allConsumecustody.getId());

                allConsumeCustodyss.add(consumeCustodyDto);
            }
            int total = iConsumeCustodyService.getCount();
            map.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            map.put("data",allConsumeCustodyss);
            map.put("total",total);
            return JSON.toJSONString(map);
        }else{
            map.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(map);
        }
    }

    @ApiOperation(value="获得保管领用", notes="根据传过来的设备信息来获得保管领用")
    @GetMapping(value = "/allconsumecustody/pagenum/pagesize",produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustody(@PathVariable("pagenum") int pageNum,@PathVariable("pagesize") int pageSize){
        Map<Object,Object> allConsumeCustodyMap = iConsumeCustodyService.findAll(pageNum,pageSize);
        if(!allConsumeCustodyMap.isEmpty()){
            allConsumeCustodyMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }else{
            allConsumeCustodyMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }
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
        if(i<1){
            insertConsumeTypeMap.put("status","failure");
            return JSON.toJSONString(insertConsumeTypeMap);
        }else{
            insertConsumeTypeMap.put("status","success");
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
        if(i<1){
            updateConsumeTypeMap.put("status","failure");
            return JSON.toJSONString(updateConsumeTypeMap);
        }else{
            updateConsumeTypeMap.put("status","success");
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
    @ApiOperation(value="通过id查找所有耗材类型信息", notes="根据传过来的id来查询耗材类型信息")
    @GetMapping(value="allconsumebytype",produces = "text/plain;charset=utf-8")
    public String allConsumeByType(int type){
        Map<Object, Object> AllConsumeByTypeIdMap = iConsumeInformService.findAllConfumeInformByTypeId(type);
        if(!AllConsumeByTypeIdMap.isEmpty()){
            AllConsumeByTypeIdMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(AllConsumeByTypeIdMap);
        }else{
            AllConsumeByTypeIdMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(AllConsumeByTypeIdMap);
        }
    }

    @ApiOperation(value="查询所有耗材和耗材类型", notes="查询所有耗材和耗材类型")
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
}
