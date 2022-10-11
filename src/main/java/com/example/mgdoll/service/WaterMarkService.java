package com.example.mgdoll.service;

import com.example.mgdoll.model.MgWaterMark;

import java.util.List;

public interface WaterMarkService {
    void insert(MgWaterMark waterMark);

    List<MgWaterMark> queryWaterListByUserId(String userId);

    MgWaterMark updateStatus(MgWaterMark waterMark);

    List<MgWaterMark> queryWaterListByStatus(Integer status);
}
