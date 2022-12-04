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

    @Override
    public Integer selectExist(MgConfigData configData) {
        MgConfigData entity = mgConfigDataMapper.selectByConfigName(configData.getConfigName());
        if(entity != null){
            return 1;
        }
        return 0;
    }

    @Override
    public void updateData(MgConfigData configData) {
        mgConfigDataMapper.updateDataByConfigName(configData);
    }

    @Override
    public void insert(MgConfigData configData) {
        mgConfigDataMapper.insert(configData);
    }
}
