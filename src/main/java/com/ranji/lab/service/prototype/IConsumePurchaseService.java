package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.ConsumePurchase;

import java.util.List;
import java.util.Map;

public interface IConsumePurchaseService {

    int insertConsumePurchase(ConsumePurchase consumePurchase);

    int updateConsumePurchase(ConsumePurchase consumePurchase);

    Map<Object,Object> findAllConsumePurchase(int pageNum , int pageSize);

    List<ConsumePurchase> findAllConsumePurchase();

    ConsumePurchase findById(int id);
}
