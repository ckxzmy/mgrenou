package com.example.mgdoll.controller;

import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.model.ResContent;
import com.example.mgdoll.service.ManageUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/manageUser")
public class ManageUserController {

    @Autowired
    private ManageUserInfoService manageUserInfoService;

    @PostMapping("/login")
    @ResponseBody
    public ResContent login(@RequestBody ManageUserInfo userInfo){
        ResContent resContent = new ResContent();
        resContent.setStatusCode("OK");
        if(userInfo != null && StringUtils.isNotEmpty(userInfo.getUserName()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
            ManageUserInfo existUserInfo = manageUserInfoService.loginByInfo(userInfo);
            if(existUserInfo != null){
                resContent.setModel(existUserInfo);
            }
        }
        return resContent;
    }
}
