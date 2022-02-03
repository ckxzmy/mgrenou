package com.example.mgdoll.service;

import com.example.mgdoll.model.AppUserInfo;

public interface AppUserInfoService {
    AppUserInfo loginByInfo(AppUserInfo userInfo);

    Integer selectExistUserByMobile(AppUserInfo userInfo);

    void insert(AppUserInfo userInfo);
}
