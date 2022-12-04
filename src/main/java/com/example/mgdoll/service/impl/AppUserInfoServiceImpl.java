package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.AppUserInfoMapper;
import com.example.mgdoll.mapper.UserLoginLogMapper;
import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.model.UserLoginLog;
import com.example.mgdoll.service.AppUserInfoService;
import com.example.mgdoll.vo.AppUserInfoVO;
import com.example.mgdoll.vo.UserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserInfoServiceImpl implements AppUserInfoService {

    @Autowired
    private AppUserInfoMapper appUserInfoMapper;
    @Autowired
    private UserLoginLogMapper logMapper;

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

    @Override
    public void update(AppUserInfoVO userInfo) {
        appUserInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public AppUserInfo selectExistUser(AppUserInfoVO userInfo) {
        return appUserInfoMapper.selectByPrimaryKey(userInfo.getUserId());
    }

    @Override
    public List<AppUserInfo> queryBySearchCondition(UserSearchVO userSearchVO) {
        return appUserInfoMapper.queryBySearchCondition(userSearchVO);
    }

    @Override
    public void insertLog(UserLoginLog loginLog) {
        logMapper.insert(loginLog);
    }
}
