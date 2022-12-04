package com.example.mgdoll.service;

import com.example.mgdoll.model.AppUserFinance;

public interface AppUserFinanceService {
    void insert(AppUserFinance finance);

    AppUserFinance selectByAppUserId(String userId);

    void update(AppUserFinance finance);

    void recharge(AppUserFinance finance);
}
