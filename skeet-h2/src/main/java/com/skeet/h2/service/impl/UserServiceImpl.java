package com.skeet.h2.service.impl;

import com.skeet.h2.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/3/18 19:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getUserName() {
        return "my_heart";
    }
}
