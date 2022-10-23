package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgSuitMapper;
import com.example.mgdoll.model.MgSuit;
import com.example.mgdoll.service.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuitServiceImpl implements SuitService {
    @Autowired
    private MgSuitMapper suitMapper;

    @Override
    public int insert(MgSuit mgSuit) {
        int num = suitMapper.insert(mgSuit);
        return num;
    }

    @Override
    public void update(MgSuit mgSuit) {
        suitMapper.updateByPrimaryKey(mgSuit);
    }
}
