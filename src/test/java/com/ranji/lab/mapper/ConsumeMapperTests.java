package com.ranji.lab.mapper;

import com.github.pagehelper.PageHelper;
import com.ranji.lab.dto.UserDto;
import com.ranji.lab.entity.ConsumePurchase;
import com.ranji.lab.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserMapper单元测试类
 *
 * @author RanJi
 */
@SpringBootTest
public class ConsumeMapperTests {
    @Resource
    ConsumePurchaseMapper consumePurchaseMapper;

    @Test
    public void testUpdate() {
        List<ConsumePurchase> all = consumePurchaseMapper.statusFindAll(0);
        for (ConsumePurchase consumePurchase : all) {
            System.out.println(consumePurchase);
        }
    }

}
