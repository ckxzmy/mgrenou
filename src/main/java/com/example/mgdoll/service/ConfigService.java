package com.example.mgdoll.service;

import com.example.mgdoll.model.MgConfigData;

public interface ConfigService {
    MgConfigData selectByConfigName(String configName);
}
