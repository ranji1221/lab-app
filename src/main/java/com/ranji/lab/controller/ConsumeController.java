package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.*;
import com.ranji.lab.service.prototype.IConsumeCustodyService;
import com.ranji.lab.service.prototype.IConsumeInformService;
import com.ranji.lab.service.prototype.IConsumeNormService;
import com.ranji.lab.service.prototype.IConsumePurchaseService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    /**
     * 插入设备信息
     * @return
     */
    @ApiOperation(value="插入设备信息", notes="根据传过来的设备信息来插入设备信息")
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
    @PostMapping(value="/insertconsumeinform",produces = "text/plain;charset=utf-8")
    public String insertConsumeInform(ConsumeInform consumeInform){
        Map<Object,Object> insertConsumeInformMap = new HashMap<>();
        int i = iConsumeInformService.insertConsumeInform(consumeInform);
        if(i<1){
            insertConsumeInformMap.put("status","failure");
            return JSON.toJSONString(insertConsumeInformMap);
        }else{
            insertConsumeInformMap.put("status","success");
            return JSON.toJSONString(insertConsumeInformMap);
        }
    }
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

    @GetMapping(value = "/findallconsumeinform",produces = "text/plain;charset=utf-8")
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
    @GetMapping(value = "/findallconsumeinform/pagenum/pagesize",produces = "text/plain;charset=utf-8")
    public String findAllCOnsumeInform(@PathVariable("pagenum") int pageNum , @PathVariable("pagesize") int pageSize){
        Map<Object,Object> allConsumeInform = iConsumeInformService.findAllConsumeInform(pageNum,pageSize);
        if(!allConsumeInform.isEmpty()){
            allConsumeInform.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeInform);
        }else{
            allConsumeInform.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeInform);
        }
    }

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

    @GetMapping(value = "/findallconsumepurchase",produces = "text/plain;charset=utf-8")
    public String findAllConsumePurchase(){
        List<ConsumePurchase> allConsumePurchase = iConsumePurchaseService.findAllConsumePurchase();
        Map<Object,Object> consumePurchaseMap = new HashMap<>();
        if(!allConsumePurchase.isEmpty()){
            consumePurchaseMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            consumePurchaseMap.put("data",consumePurchaseMap);
            return JSON.toJSONString(consumePurchaseMap);
        }else{
            consumePurchaseMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(consumePurchaseMap);
        }
    }
    @GetMapping(value = "/findallconsumepurchase/pagenum/pagesize",produces = "text/plain;charset=utf-8")
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

    @PostMapping(value = "/insertconsumenorm",produces = "text/plain;chartset=utf-8")
    public String insertConsumeNorm(ConsumeNorm consumeNorm){
        Map<Object, Object> insertConsumeNormMap = new HashMap<>();
        int i = iConsumeNormService.insertConsumeNorm(consumeNorm);
        if(i<1){
            insertConsumeNormMap.put("status","failure");
            return JSON.toJSONString(insertConsumeNormMap);
        }else{
            insertConsumeNormMap.put("status","success");
            return JSON.toJSONString(insertConsumeNormMap);
        }
    }

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


    @GetMapping(value = "/findallconsumenorm",produces = "text/plain;charset=utf-8")
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
    }

    @GetMapping(value = "/findallconsumenorm/pagenum/pagesize",produces = "text/plain;charset=utf-8")
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
    @GetMapping(value = "/findallconsumecustody",produces = "text/plain;charset=utf-8")
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

    @GetMapping(value = "/findallconsumecustody/pagenum/pagesize",produces = "text/plain;charset=utf-8")
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


    @PostMapping(value = "/insertconsumetype",produces = "text/plain;charset=utf-8")
    public String aaa(){
        return JSON.toJSONString("");
    }
}
