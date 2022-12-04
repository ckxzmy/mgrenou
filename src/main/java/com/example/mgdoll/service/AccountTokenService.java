package com.example.mgdoll.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface AccountTokenService {
    void saveToken(Object object, String flag);

    Boolean verifyExpire(String phone, String token);

    void updateToken(HttpServletRequest request);

    void updateTokenTest(String s, Date date);

    boolean checkManage(HttpServletRequest request);
    boolean checkAPP(HttpServletRequest request);
}
