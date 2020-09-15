package com.ranji.lab.mapper;

import com.ranji.lab.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ClassName:    EmployeeMapperTests
 * Package:    com.ranji.lab.mapper
 * Description:
 * Datetime:    2020/9/15   6:42 下午
 * Author:   ranji
 */

@SpringBootTest
public class EmployeeMapperTests {
    @Resource
    private EmployeeMapper empMapper;

    @Test
    public void testFind(){
        Employee emp = empMapper.find("zhangsan");
        System.out.println(emp);
    }
}