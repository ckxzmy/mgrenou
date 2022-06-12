package com.example.mgdoll.service;

import com.example.mgdoll.model.MgDoll;
import com.example.mgdoll.model.MgPart;

import java.util.List;

public interface MgPartService {

    void insertPart(List<MgPart> partList, String token, MgDoll doll);

    List<MgPart> queryPartListByDollId(Integer valueOf);

    void updatePart(List<MgPart> partList, String token, MgDoll doll);
}
