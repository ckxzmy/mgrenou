package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.NotCheckTokenAnn;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.model.ResContent;
import com.example.mgdoll.service.ManageUserInfoService;
import com.example.mgdoll.service.MgNoteService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import com.example.mgdoll.util.SmsUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/manageUser")
public class ManageUserController {

    private static Logger logger = LoggerFactory.getLogger(ManageUserController.class);

    @Autowired
    private ManageUserInfoService manageUserInfoService;

    @Autowired
    private MgNoteService mgNoteService;

    @PostMapping("/login")
    @ResponseBody
    @CrossOrigin
    public ApiResponse login(@RequestBody ManageUserInfo userInfo) throws UnsupportedEncodingException {
        ApiResponse apiResponse = new ApiResponse();
        if(userInfo != null){
            if(StringUtils.isNotEmpty(userInfo.getUserMobile()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
                String password = userInfo.getUserPassword();
                ManageUserInfo existUserInfo = manageUserInfoService.loginByInfo(userInfo);
                if(existUserInfo != null){
                    if(password.equals(existUserInfo.getUserPassword())){
                        String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()));
                        existUserInfo.setToken(token);
                        apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
                    }else {
                        apiResponse = ApiResponseUtil.getApiResponse(-101,"密码不正确！");
                    }
                }else apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.LOGIN_FAIL);
            }else if(StringUtils.isNotEmpty(userInfo.getUserMobile()) && StringUtils.isNotEmpty(userInfo.getAuthCode())){
                HashMap<String,String> checkResult = mgNoteService.checkAuthCode(userInfo.getUserMobile(),userInfo.getAuthCode());
                if(checkResult != null){
                    if("200".equals(checkResult.get("code"))){
                        ManageUserInfo existUserInfo = manageUserInfoService.loginByInfo(userInfo);
                        if(existUserInfo != null){
                            String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()));
                            existUserInfo.setToken(token);
                            apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
                        }else apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.LOGIN_FAIL);
                    }else {
                        apiResponse = ApiResponseUtil.getApiResponse(-1,checkResult.get("message"));
                        return apiResponse;
                    }
                }
            }else {
                apiResponse = ApiResponseUtil.getApiResponse(-101,"请正确登录！");
                return apiResponse;
            }

        }

        return apiResponse;
    }

}
