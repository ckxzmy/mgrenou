package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.NotCheckTokenAnn;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.model.ResContent;
import com.example.mgdoll.service.ManageUserInfoService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
@RequestMapping("/manageUser")
public class ManageUserController {

    @Autowired
    private ManageUserInfoService manageUserInfoService;

    @PostMapping("/login")
    //@NotCheckTokenAnn
    @ResponseBody
    public ApiResponse login(@RequestBody ManageUserInfo userInfo) throws UnsupportedEncodingException {
        ApiResponse apiResponse = new ApiResponse();
        if(userInfo != null && StringUtils.isNotEmpty(userInfo.getUserMobile()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
            ManageUserInfo existUserInfo = manageUserInfoService.loginByInfo(userInfo);
            if(existUserInfo != null){
                String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()));
                existUserInfo.setToken(token);
                apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
            }else ApiResponseUtil.getApiResponse(ApiResponseEnum.LOGIN_FAIL);
        }

        return apiResponse;
    }
}
