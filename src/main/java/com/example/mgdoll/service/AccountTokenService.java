package com.example.mgdoll.service;

import javax.servlet.http.HttpServletRequest;

public interface AccountTokenService {
    void saveToken(Object object, String flag);

    Boolean verifyExpire(String phone, String token);

    void updateToken(HttpServletRequest request);
}
