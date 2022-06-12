package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.NotCheckTokenAnn;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.service.AppUserInfoService;
import com.example.mgdoll.service.MgNoteService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/appUser")
public class AppUserController {
    private static Logger logger = LoggerFactory.getLogger(AppUserController.class);
    //验证码超过30分钟失效
    private static final int DIFF_DATE = 30*60*1000;

    @Autowired
    private AppUserInfoService appUserInfoService;

    @Autowired
    private MgNoteService mgNoteService;

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse login(@RequestBody AppUserInfo userInfo) throws UnsupportedEncodingException {
        ApiResponse apiResponse = new ApiResponse();
        if(userInfo != null && StringUtils.isNotEmpty(userInfo.getUserMobile()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
            Boolean codeFlag = true;
            if(StringUtils.isNotEmpty(userInfo.getAuthCode())){
                Date insertTime = mgNoteService.getLastInsertTimeByMobile(userInfo.getUserMobile(),userInfo.getAuthCode());
                if(insertTime != null){
                    Long diff = System.currentTimeMillis()-insertTime.getTime();
                    logger.info("该验证码接收时间为：{}，距离当前时间：{}ms",insertTime,System.currentTimeMillis()-insertTime.getTime());
                    if(diff >= 0 && diff <= DIFF_DATE){
                        codeFlag = true;
                    }else apiResponse = ApiResponseUtil.getApiResponse(-1,"验证码失效！");
                }
            }else apiResponse = ApiResponseUtil.getApiResponse(-1,"验证码为空！");
            if(codeFlag){
                AppUserInfo existUserInfo = appUserInfoService.loginByInfo(userInfo);
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
    public ApiResponse register(@RequestBody AppUserInfo userInfo){
        ApiResponse apiResponse = new ApiResponse();
        if(userInfo != null){
            Integer existNum = appUserInfoService.selectExistUserByMobile(userInfo);
            if(existNum >0){
                apiResponse = ApiResponseUtil.getApiResponse(-1,"This mobile is exist!");
            }else {
                userInfo.setUserId(UUID.randomUUID().toString().replace("-",""));
                userInfo.setInsertTime(new Date());
                appUserInfoService.insert(userInfo);
                apiResponse = ApiResponseUtil.getApiResponse(userInfo);
                logger.info("注册成功");
            }
        }
        return apiResponse;
    }
}
