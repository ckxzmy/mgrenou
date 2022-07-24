package com.example.mgdoll.service;

import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.vo.ManageUserInfoVO;

public interface ManageUserInfoService {
    ManageUserInfo loginByInfo(ManageUserInfoVO userInfo);

    int selectExistUserByMobile(ManageUserInfo userInfo);

    void insert(ManageUserInfo userInfo);
}
