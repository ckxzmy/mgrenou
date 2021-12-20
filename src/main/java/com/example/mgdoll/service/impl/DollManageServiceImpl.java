package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgDollMapper;
import com.example.mgdoll.service.DollManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DollManageServiceImpl implements DollManageService {

    @Autowired
    private MgDollMapper mgDollMapper;
}
