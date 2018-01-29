package com.qianfeng.provider.service.impl;

import com.qianfeng.provider.service.DemoService;

public class DemoServiceImpl implements DemoService{
    @Override
    public String demo(String str) {
        return str+"hello";
    }
}
