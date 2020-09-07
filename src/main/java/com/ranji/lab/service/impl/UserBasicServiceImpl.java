package com.ranji.lab.service.impl;

import com.ranji.lab.dto.UserBasicDto;
import com.ranji.lab.entity.UserBasic;
import com.ranji.lab.mapper.UserBasicMapper;
import com.ranji.lab.service.prototype.IUserBasicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserBasicServiceImpl implements IUserBasicService {
    @Resource
    private UserBasicMapper userBasicMapper;

    @Override
    public int insertUserBasic(UserBasic userBasic) {
        return userBasicMapper.insertUserBasic(userBasic);
    }

    @Override
    public UserBasicDto findUserBasic(int userId) {
        return userBasicMapper.findByUserId(userId);
    }
}
