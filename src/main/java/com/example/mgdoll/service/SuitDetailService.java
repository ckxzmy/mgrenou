package com.example.mgdoll.service;

import com.example.mgdoll.model.MgSuit;
import com.example.mgdoll.model.MgSuitDetail;

import java.util.List;

public interface SuitDetailService {

    void insertDetailBySuitId(List<MgSuitDetail> mgSuitDetails, String token, MgSuit mgSuit);

    void insertORUpateDetailBySuitId(List<MgSuitDetail> mgSuitDetails, String token, MgSuit mgSuit);
}
