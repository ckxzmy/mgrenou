package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.AppUserInfoMapper;
import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.service.AppUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserInfoServiceImpl implements AppUserInfoService {

    @Autowired
    private AppUserInfoMapper appUserInfoMapper;

    @Override
    public AppUserInfo loginByInfo(AppUserInfo userInfo) {
        userInfo = appUserInfoMapper.selectByInfo(userInfo);
        return userInfo;
    }

    @Override
    public Integer selectExistUserByMobile(AppUserInfo userInfo) {
        int number = appUserInfoMapper.selectExistUserByMobile(userInfo);
        return number;
    }

    @Override
    public void insert(AppUserInfo userInfo) {
        appUserInfoMapper.insert(userInfo);
    }
}
