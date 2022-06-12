package com.example.mgdoll.service;

import com.example.mgdoll.model.MgColorGroup;

import java.util.List;

public interface MgColorService {
    void insertColor(List<MgColorGroup> colorGroupList, String token);

    void updateColor(List<MgColorGroup> colorGroupList, String token);
}
