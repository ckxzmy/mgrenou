package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgColorGroupMapper;
import com.example.mgdoll.mapper.MgColorMapper;
import com.example.mgdoll.model.MgColor;
import com.example.mgdoll.model.MgColorGroup;
import com.example.mgdoll.service.MgColorService;
import com.example.mgdoll.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MgColorServiceImpl implements MgColorService {

    @Autowired
    private MgColorGroupMapper cgMapper;
    @Autowired
    private MgColorMapper colorMapper;

    @Override
    public void insertColor(List<MgColorGroup> colorGroupList, String token) {
        String userId = JwtUtil.getUserId(token);
        for(MgColorGroup colorGroup : colorGroupList){
            colorGroup.setInsertTime(new Date());
            colorGroup.setInsertBy(userId);
            int num = cgMapper.insert(colorGroup);
            if(num > 0 && colorGroup.getColorArray() != null && colorGroup.getColorArray().size() > 0){
                for(MgColor mgColor: colorGroup.getColorArray()){
                    mgColor.setGroupId(colorGroup.getGroupId());
                    mgColor.setInsertTime(new Date());
                    mgColor.setInsertBy(userId);
                    mgColor.setOwnerId(userId);
                    colorMapper.insert(mgColor);
                }
            }
            System.out.println(colorGroup.getGroupId());
        }
    }

    @Override
    public void updateColor(List<MgColorGroup> colorGroupList, String token) {
        String userId = JwtUtil.getUserId(token);
        for(MgColorGroup colorGroup : colorGroupList){
            if(colorGroup.getGroupId() != null){
                cgMapper.updateByPrimaryKey(colorGroup);
                if(colorGroup.getColorArray() != null && colorGroup.getColorArray().size() > 0){
                    for(MgColor mgColor: colorGroup.getColorArray()){
                        if(mgColor.getColorId()==null){
                            mgColor.setGroupId(colorGroup.getGroupId());
                            mgColor.setInsertTime(new Date());
                            mgColor.setInsertBy(userId);
                            mgColor.setOwnerId(userId);
                            colorMapper.insert(mgColor);
                        }else {
                            colorMapper.updateByPrimaryKey(mgColor);
                        }

                    }
                }
            }else {
                colorGroup.setInsertTime(new Date());
                colorGroup.setInsertBy(userId);
                int num = cgMapper.insert(colorGroup);
                if(num > 0 && colorGroup.getColorArray() != null && colorGroup.getColorArray().size() > 0){
                    for(MgColor mgColor: colorGroup.getColorArray()){
                        mgColor.setGroupId(colorGroup.getGroupId());
                        mgColor.setInsertTime(new Date());
                        mgColor.setInsertBy(userId);
                        mgColor.setOwnerId(userId);
                        colorMapper.insert(mgColor);
                    }
                }
            }

        }
    }
}
