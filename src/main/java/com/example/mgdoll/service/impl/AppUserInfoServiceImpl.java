package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.AppUserInfoMapper;
import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.service.AppUserInfoService;
import com.example.mgdoll.vo.AppUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserInfoServiceImpl implements AppUserInfoService {

    @Autowired
    private AppUserInfoMapper appUserInfoMapper;

    @Override
    public AppUserInfo loginByInfo(AppUserInfoVO userInfo) {
        AppUserInfo user = appUserInfoMapper.selectByInfo(userInfo);
        return user;
    }

    @Override
    public Integer selectExistUserByMobile(AppUserInfoVO userInfo) {
        int number = appUserInfoMapper.selectExistUserByMobile(userInfo);
        return number;
    }

    @Override
    public void insert(AppUserInfoVO userInfo) {
        appUserInfoMapper.insert(userInfo);
    }
}
