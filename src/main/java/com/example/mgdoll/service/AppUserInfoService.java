package com.example.mgdoll.service;

import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.vo.AppUserInfoVO;

public interface AppUserInfoService {
    AppUserInfo loginByInfo(AppUserInfoVO userInfo);

    Integer selectExistUserByMobile(AppUserInfoVO userInfo);

    void insert(AppUserInfoVO userInfo);
}
