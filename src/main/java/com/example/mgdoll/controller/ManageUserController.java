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
    public ApiResponse login(@RequestBody ManageUserInfo userInfo) throws UnsupportedEncodingException {
        ApiResponse apiResponse = new ApiResponse();
        if(userInfo != null && StringUtils.isNotEmpty(userInfo.getUserMobile()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
            Boolean codeFlag = false;
            if(StringUtils.isNotEmpty(userInfo.getAuthCode())){
                HashMap<String,String> checkResult = mgNoteService.checkAuthCode(userInfo.getUserMobile(),userInfo.getAuthCode());
                if(checkResult != null){
                    if("200".equals(checkResult.get("code"))){
                        codeFlag = true;
                    }else {
                        apiResponse = ApiResponseUtil.getApiResponse(-1,checkResult.get("message"));
                        return apiResponse;
                    }
                }
            }else {
                apiResponse = ApiResponseUtil.getApiResponse(-1,"验证码为空！");
                return apiResponse;
            }
            if(codeFlag){
                ManageUserInfo existUserInfo = manageUserInfoService.loginByInfo(userInfo);
                if(existUserInfo != null){
                    String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()));
                    existUserInfo.setToken(token);
                    apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
                    System.setProperty("user.name",existUserInfo.getUserMobile());
                    System.setProperty("user.id",existUserInfo.getUserId());
                }else apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.LOGIN_FAIL);
            }

        }

        return apiResponse;
    }

    @PostMapping("/register")
    @NotCheckTokenAnn
    @ResponseBody
    public ApiResponse register(@RequestBody ManageUserInfo userInfo){
        ApiResponse apiResponse = new ApiResponse();
        if(userInfo != null){
            Integer existNum = manageUserInfoService.selectExistUserByMobile(userInfo);
            if(existNum >0){
                apiResponse = ApiResponseUtil.getApiResponse(-1,"This mobile is exist!");
            }else {
                if(userInfo != null && StringUtils.isNotEmpty(userInfo.getUserMobile()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
                    if(StringUtils.isNotEmpty(userInfo.getAuthCode())){
                        HashMap<String,String> checkResult = mgNoteService.checkAuthCode(userInfo.getUserMobile(),userInfo.getAuthCode());
                        if(checkResult != null){
                            if("200".equals(checkResult.get("code"))){
                                userInfo.setUserId(UUID.randomUUID().toString().replace("-",""));
                                userInfo.setInsertTime(new Date());
                                manageUserInfoService.insert(userInfo);
                                apiResponse = ApiResponseUtil.getApiResponse(userInfo);
                                logger.info("注册成功");
                            }else {
                                apiResponse = ApiResponseUtil.getApiResponse(-1,checkResult.get("message"));
                                return apiResponse;
                            }
                        }
                    }else {
                        apiResponse = ApiResponseUtil.getApiResponse(-1,"验证码为空！");
                        return apiResponse;
                    }
                }
            }
        }
        return apiResponse;
    }
}
