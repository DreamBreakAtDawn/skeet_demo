package com.skeet.h2.service.impl;

import com.skeet.h2.service.BaseService;
import com.skeet.h2.service.CompanyService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author chengsj
 * @Date 2021/3/22 14:01
 */
@Service
public class CompanyServiceImpl implements CompanyService, BaseService {

    @Override
    public String getUserName() {
        System.out.println("CompanyServiceImpl getUserName...");
        return "my_heart";
    }

    @Override
    public void baseOption() {
        System.out.println("CompanyServiceImpl baseOption...");
    }
}
