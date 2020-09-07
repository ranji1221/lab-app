package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.UserBasicDto;
import com.ranji.lab.entity.UserBasic;

public interface IUserBasicService {
    int insertUserBasic(UserBasic userBasic);

    UserBasicDto findUserBasic(int userId);
}
