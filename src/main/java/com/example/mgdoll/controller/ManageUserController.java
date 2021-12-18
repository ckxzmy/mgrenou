package com.example.mgdoll.controller;

import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.service.ManageUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manageUser")
public class ManageUserController {

    @Autowired
    private ManageUserInfoService manageUserInfoService;

    @PostMapping("/login")
    public Map login(@RequestBody ManageUserInfo userInfo){
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("status","OK");
        if(userInfo != null && StringUtils.isNotEmpty(userInfo.getUserName()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
            ManageUserInfo existUserInfo = manageUserInfoService.loginByInfo(userInfo);
            if(existUserInfo != null){
                requestMap.put("manageUser",existUserInfo);
            }
        }
        return requestMap;
    }
}
