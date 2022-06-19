package com.example.mgdoll.service;

import com.example.mgdoll.model.MgNoteInfo;

import java.util.Date;
import java.util.HashMap;

public interface MgNoteService {
    void insert(MgNoteInfo mgNoteInfo);

    Date getLastInsertTimeByMobile(String userMobile, String authCode, String flag);

    HashMap<String, String> checkAuthCode(String userMobile,String authCode, String flag);
}
