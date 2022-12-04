package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.AppUserRechargeMapper;
import com.example.mgdoll.model.AppUserRecharge;
import com.example.mgdoll.service.AppUserRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserRechargeServiceImpl implements AppUserRechargeService {

    @Autowired
    private AppUserRechargeMapper rechargeMapper;

    @Override
    public AppUserRecharge selectByFinanceID(Integer id) {
        return rechargeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(AppUserRecharge rechargeLog) {
        rechargeMapper.insert(rechargeLog);
    }
}
