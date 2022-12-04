package com.example.mgdoll.service;

import com.example.mgdoll.model.MgConfigData;

public interface ConfigService {
    MgConfigData selectByConfigName(String configName);

    Integer selectExist(MgConfigData configData);

    void updateData(MgConfigData configData);

    void insert(MgConfigData configData);
}
