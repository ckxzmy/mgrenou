package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.AppUserFinanceMapper;
import com.example.mgdoll.model.AppUserFinance;
import com.example.mgdoll.service.AppUserFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserFinanceServiceImpl implements AppUserFinanceService {

    @Autowired
    private AppUserFinanceMapper financeMapper;

    @Override
    public void insert(AppUserFinance finance) {
        financeMapper.insert(finance);
    }

    @Override
    public AppUserFinance selectByAppUserId(String userId) {
        return financeMapper.selectByAppUserId(userId);
    }

    @Override
    public void update(AppUserFinance finance) {

    }

    @Override
    public void recharge(AppUserFinance finance) {
        financeMapper.recharge(finance);
    }
}
