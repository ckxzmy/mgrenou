package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgWaterMarkMapper;
import com.example.mgdoll.model.MgWaterMark;
import com.example.mgdoll.service.WaterMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterMarkServiceImpl implements WaterMarkService {
    @Autowired
    private MgWaterMarkMapper waterMarkMapper;

    @Override
    public void insert(MgWaterMark waterMark) {
        waterMarkMapper.insert(waterMark);
    }

    @Override
    public List<MgWaterMark> queryWaterListByUserId(String userId) {
        List<MgWaterMark> waterList = waterMarkMapper.queryWaterListByUserId(userId);
        return waterList;
    }

    @Override
    public MgWaterMark updateStatus(MgWaterMark waterMark) {
        int num = waterMarkMapper.updateStatus(waterMark);
        if(num == 1){
            MgWaterMark mgWaterMark = waterMarkMapper.selectByPrimaryKey(waterMark.getId());
            return mgWaterMark;
        }
        return null;
    }

    @Override
    public List<MgWaterMark> queryWaterListByStatus(Integer status) {
        List<MgWaterMark> waterList = waterMarkMapper.queryWaterListByStatus(status);
        return waterList;
    }
}
