package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ConsumePurchaseDto;
import com.ranji.lab.entity.ConsumeInform;
import com.ranji.lab.entity.ConsumePurchase;
import com.ranji.lab.mapper.ConsumeInformMapper;
import com.ranji.lab.mapper.ConsumePurchaseMapper;
import com.ranji.lab.service.prototype.IConsumePurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsumePurchaseServiceImpl implements IConsumePurchaseService {

    @Resource
    private ConsumePurchaseMapper consumePurchaseMapper;
    @Resource
    private ConsumeInformMapper consumeInformMapper;


    @Override
    public int insertConsumePurchase(ConsumePurchase consumePurchase) {
        return consumePurchaseMapper.insertConsumePurchase(consumePurchase);
    }

    @Override
    @Transactional
    public int updateConsumePurchase(ConsumePurchase consumePurchase) {
        System.out.println(consumePurchase.getApplicant());
        if (consumePurchase.getApplicant() == null) {
            ConsumeInform consumeInform = new ConsumeInform();
            consumeInform.setId(consumePurchase.getConsumeId());
            consumeInform.setNum(consumePurchase.getNum());
            consumeInformMapper.updateConsumeInformNum(consumeInform);
            return consumePurchaseMapper.updateConsumePurchaseStatus(consumePurchase);
        } else {
            return consumePurchaseMapper.updateConsumePurchase(consumePurchase);
        }
    }

    @Override
    public Map<Object, Object> findAllConsumePurchase(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumePurchase> allConsumePurchase = consumePurchaseMapper.findAll();

        PageInfo<ConsumePurchase> allConsumePurchasePaging = new PageInfo<>(allConsumePurchase);
        long total = allConsumePurchasePaging.getTotal();

        Map<Object,Object> allConsumePurchaseOnPaging = new HashMap<>();
        allConsumePurchaseOnPaging.put("data",allConsumePurchase);
        allConsumePurchaseOnPaging.put("total",total);

        return allConsumePurchaseOnPaging;
    }

    @Override
    public List<ConsumePurchase> findAllConsumePurchases(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumePurchase> all = consumePurchaseMapper.findAll();

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);
        return all;
    }

    @Override
    public List<ConsumePurchase> findAllConsumePurchase() {
        return consumePurchaseMapper.findAll();
    }

    @Override
    public ConsumePurchase findById(int id) {
        return consumePurchaseMapper.findById(id);
    }

    @Override
    public ConsumePurchaseDto findNameById(int id) {
        return consumePurchaseMapper.findNameById(id);
    }

    @Override
    public int getCount() {
        return consumePurchaseMapper.getCount();
    }

    /**
     * 按照状态查询购置
     *
     * @param status
     * @return
     */
    @Override
    public Map<Object, Object> statusFindAll(Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConsumePurchase> allConsumePurchase = consumePurchaseMapper.statusFindAll(status);

        PageInfo<ConsumePurchase> allConsumePurchasePaging = new PageInfo<>(allConsumePurchase);
        long total = allConsumePurchasePaging.getTotal();

        Map<Object, Object> allConsumePurchaseOnPaging = new HashMap<>();
        allConsumePurchaseOnPaging.put("data", allConsumePurchase);
        allConsumePurchaseOnPaging.put("total", total);

        return allConsumePurchaseOnPaging;
    }

    @Override
    public Map<Object, Object> likeFindAll(int pageNum, int pageSize, String like) {
        PageHelper.startPage(pageNum, pageSize);
        List<ConsumePurchase> allConsumePurchase = consumePurchaseMapper.likeFindAll(like);

        PageInfo<ConsumePurchase> allConsumePurchasePaging = new PageInfo<>(allConsumePurchase);
        long total = allConsumePurchasePaging.getTotal();

        Map<Object, Object> allConsumePurchaseOnPaging = new HashMap<>();
        allConsumePurchaseOnPaging.put("data",allConsumePurchase);
        allConsumePurchaseOnPaging.put("total",total);

        return allConsumePurchaseOnPaging;
    }

    @Override
    public Map<Object, Object> likeFindAll(String like) {
        List<ConsumePurchase> consumePurchases = consumePurchaseMapper.likeFindAll(like);
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data",consumePurchases);
        allMap.put("tatol",consumePurchases.size());
        return allMap;
    }
}
