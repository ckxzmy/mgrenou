package com.example.mgdoll.service;

import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.model.UserLoginLog;
import com.example.mgdoll.vo.AppUserInfoVO;
import com.example.mgdoll.vo.UserSearchVO;

import java.util.List;

public interface AppUserInfoService {
    AppUserInfo loginByInfo(AppUserInfoVO userInfo);

    Integer selectExistUserByMobile(AppUserInfoVO userInfo);

    void insert(AppUserInfoVO userInfo);

    void update(AppUserInfoVO userInfo);

    AppUserInfo selectExistUser(AppUserInfoVO userInfo);

    List<AppUserInfo> queryBySearchCondition(UserSearchVO userSearchVO);

    void insertLog(UserLoginLog loginLog);
}
