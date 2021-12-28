package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgColorGroupMapper;
import com.example.mgdoll.model.MgColorGroup;
import com.example.mgdoll.service.MgColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MgColorServiceImpl implements MgColorService {



    @Autowired
    private MgColorGroupMapper cgMapper;


    @Override
    public void insertColor(List<MgColorGroup> colorGroupList) {
        for(MgColorGroup colorGroup : colorGroupList){
            colorGroup.setInsertTime(new Date());
            colorGroup.setInsertBy(111);
            int id = cgMapper.insert(colorGroup);
            System.out.println(colorGroup.getGroupId());
        }
    }
}
