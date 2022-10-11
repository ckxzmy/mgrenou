package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgConfigDataMapper;
import com.example.mgdoll.model.MgConfigData;
import com.example.mgdoll.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    MgConfigDataMapper mgConfigDataMapper;

    @Override
    public MgConfigData selectByConfigName(String configName) {
        MgConfigData entity = mgConfigDataMapper.selectByConfigName(configName);
        return entity;
    }
}
