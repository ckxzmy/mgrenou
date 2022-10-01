package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.CommonConf;
import com.example.mgdoll.conf.NotCheckTokenAnn;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.service.AccountTokenService;
import com.example.mgdoll.service.AppUserInfoService;
import com.example.mgdoll.service.MgNoteService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import com.example.mgdoll.vo.AppUserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Api("manage用户信息")
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
    @Autowired
    private AccountTokenService accountTokenService;

    @PostMapping("/login")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "app用户登录",notes = "app用户登录")
    public ApiResponse login(@RequestBody AppUserInfoVO userInfo) throws UnsupportedEncodingException {
        ApiResponse apiResponse = new ApiResponse();

        if(userInfo != null){
            if(CommonConf.LOGIN_TYPE_PASSWORD.equals(userInfo.getLoginType())){
                String password = userInfo.getUserPassword();
                AppUserInfo existUserInfo = appUserInfoService.loginByInfo(userInfo);
                if(existUserInfo != null){
                    if(password.equals(existUserInfo.getUserPassword())){
                        String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()));
                        existUserInfo.setToken(token);
                        accountTokenService.saveToken(existUserInfo, CommonConf.APP_FLAG);
                        existUserInfo.setUserId(null);
                        existUserInfo.setUserPassword(null);
                        apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
                    }else {
                        apiResponse = ApiResponseUtil.getApiResponse(-101,"密码不正确！");
                    }
                }else apiResponse = ApiResponseUtil.getApiResponse(-101,"该账号未注册！");
            }else if(CommonConf.LOGIN_TYPE_MESSAGE.equals(userInfo.getLoginType())){
                HashMap<String,String> checkResult = mgNoteService.checkAuthCode(userInfo.getUserMobile(),userInfo.getAuthCode(),CommonConf.APP_FLAG);
                if(checkResult != null){
                    if("200".equals(checkResult.get("code"))){
                        AppUserInfo existUserInfo = appUserInfoService.loginByInfo(userInfo);
                        if(existUserInfo != null){
                            String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()));
                            existUserInfo.setToken(token);
                            accountTokenService.saveToken(existUserInfo, CommonConf.APP_FLAG);
                            existUserInfo.setUserId(null);
                            existUserInfo.setUserPassword(null);
                            apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
                        }else apiResponse = ApiResponseUtil.getApiResponse(-101,"该账号未注册！");
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


    @PostMapping("/register")
    @NotCheckTokenAnn
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "app用户注册",notes = "app用户注册")
    public ApiResponse register(@RequestBody AppUserInfoVO userInfo){
        ApiResponse apiResponse = new ApiResponse();
        try {
            if(userInfo != null){
                Integer existNum = appUserInfoService.selectExistUserByMobile(userInfo);
                if(existNum >0){
                    apiResponse = ApiResponseUtil.getApiResponse(-101,"This mobile is exist!");
                }else {
                    if(userInfo != null && StringUtils.isNotEmpty(userInfo.getUserMobile()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
                        if(StringUtils.isNotEmpty(userInfo.getAuthCode())){
                            HashMap<String,String> checkResult = mgNoteService.checkAuthCode(userInfo.getUserMobile(),userInfo.getAuthCode(),CommonConf.APP_FLAG);
                            if(checkResult != null){
                                if("200".equals(checkResult.get("code"))){
                                    userInfo.setUserId(UUID.randomUUID().toString().replace("-",""));
                                    userInfo.setInsertTime(new Date());
                                    appUserInfoService.insert(userInfo);
                                    userInfo.setUserId(null);
                                    userInfo.setUserPassword(null);
                                    apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
                                    logger.info("注册成功");
                                }else {
                                    apiResponse = ApiResponseUtil.getApiResponse(-101,checkResult.get("message"));
                                    return apiResponse;
                                }
                            }
                        }else {
                            apiResponse = ApiResponseUtil.getApiResponse(-101,"验证码为空！");
                            return apiResponse;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

}
