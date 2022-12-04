package com.example.mgdoll.service;

import com.example.mgdoll.model.AppUserRecharge;

public interface AppUserRechargeService {
    AppUserRecharge selectByFinanceID(Integer id);

    void insert(AppUserRecharge rechargeLog);
}
