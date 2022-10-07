package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgDollMapper;
import com.example.mgdoll.model.MgDoll;
import com.example.mgdoll.service.MgDollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MgDollServiceImpl implements MgDollService {
    @Autowired
    private MgDollMapper mgDollMapper;
    @Override
    public int insert(MgDoll doll) {
        return mgDollMapper.insert(doll);
    }

    @Override
    public MgDoll queryByDollId(Integer dollId) {
        return mgDollMapper.selectByPrimaryKey(dollId);
    }

    @Override
    public int update(MgDoll doll) {
        return mgDollMapper.updateByPrimaryKey(doll);
    }

    @Override
    public List<MgDoll> queryByOwnerId(MgDoll mgDoll) {
        List<MgDoll> list = mgDollMapper.queryByOwnerId(mgDoll);
        return list;
    }

    @Override
    public List<MgDoll> queryDollByCondition(MgDoll mgDoll) {
        List<MgDoll> list = mgDollMapper.queryDollByCondition(mgDoll);
        return list;
    }
}
