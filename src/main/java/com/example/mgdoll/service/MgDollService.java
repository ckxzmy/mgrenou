package com.example.mgdoll.service;

import com.example.mgdoll.model.MgDoll;

import java.util.List;

public interface MgDollService {
    int insert(MgDoll doll);

    MgDoll queryByDollId(Integer valueOf);

    int update(MgDoll doll);

    List<MgDoll> queryByOwnerId(MgDoll mgDoll);
}
