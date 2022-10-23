package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgSuitDetailMapper;
import com.example.mgdoll.model.MgSuit;
import com.example.mgdoll.model.MgSuitDetail;
import com.example.mgdoll.service.SuitDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SuitDetailServiceImpl implements SuitDetailService {

    @Autowired
    private MgSuitDetailMapper suitDetailMapper;

    @Override
    public void insertDetailBySuitId(List<MgSuitDetail> mgSuitDetails, String token, MgSuit mgSuit) {
        for(MgSuitDetail detail: mgSuitDetails){
            detail.setInsertTime(new Date());
            detail.setSuitId(mgSuit.getSuitId());
            suitDetailMapper.insert(detail);
        }
    }

    @Override
    public void insertORUpateDetailBySuitId(List<MgSuitDetail> mgSuitDetails, String token, MgSuit mgSuit) {
        for(MgSuitDetail detail: mgSuitDetails){
            if(detail.getDetailId() == null){
                this.insertDetailBySuitId(mgSuitDetails,token,mgSuit);
            }else {
                suitDetailMapper.updateByPrimaryKey(detail);
            }
        }
    }
}
