package com.example.mgdoll.service;

import com.example.mgdoll.model.MgNoteInfo;

import java.util.Date;

public interface MgNoteService {
    void insert(MgNoteInfo mgNoteInfo);

    Date getLastInsertTimeByMobile(String userMobile, String authCode);
}
