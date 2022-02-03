package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.ManageUserInfoMapper;
import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.service.ManageUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageUserInfoServiceImpl implements ManageUserInfoService {

    @Autowired
    private ManageUserInfoMapper manageUserInfoMapper;

    @Override
    public ManageUserInfo loginByInfo(ManageUserInfo userInfo) {
        userInfo = manageUserInfoMapper.selectByInfo(userInfo);
        return userInfo;
    }

    @Override
    public int selectExistUserByMobile(ManageUserInfo userInfo) {
        int number = manageUserInfoMapper.selectExistUserByMobile(userInfo);
        return number;
    }

    @Override
    public void insert(ManageUserInfo userInfo) {
        manageUserInfoMapper.insert(userInfo);
    }
}
