package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgNoteInfoMapper;
import com.example.mgdoll.model.MgNoteInfo;
import com.example.mgdoll.service.MgNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MgNoteServiceImpl implements MgNoteService {

    @Autowired
    private MgNoteInfoMapper mgNoteInfoMapper;

    @Override
    public void insert(MgNoteInfo mgNoteInfo) {
        mgNoteInfoMapper.insert(mgNoteInfo);
    }

    @Override
    public Date getLastInsertTimeByMobile(String userMobile, String authCode) {
        List<Date> datas = mgNoteInfoMapper.getLastInsertTimeByMobile(userMobile,authCode);
        if(datas != null && datas.size()>0){
            Date data = datas.get(0);
            return data;
        }
        return null;
    }
}
