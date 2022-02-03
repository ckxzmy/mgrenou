package com.example.mgdoll.service;

import com.example.mgdoll.model.ManageUserInfo;

public interface ManageUserInfoService {
    ManageUserInfo loginByInfo(ManageUserInfo userInfo);

    int selectExistUserByMobile(ManageUserInfo userInfo);

    void insert(ManageUserInfo userInfo);
}
