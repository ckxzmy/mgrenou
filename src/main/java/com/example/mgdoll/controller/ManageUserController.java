package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.CommonConf;
import com.example.mgdoll.conf.NotCheckTokenAnn;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.model.ResContent;
import com.example.mgdoll.service.AccountTokenService;
import com.example.mgdoll.service.ManageUserInfoService;
import com.example.mgdoll.service.MgNoteService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import com.example.mgdoll.util.SmsUtil;
import com.example.mgdoll.vo.ManageUserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Api("manage用户信息")
@RestController
@RequestMapping("/manageUser")
public class ManageUserController {

    private static Logger logger = LoggerFactory.getLogger(ManageUserController.class);

    @Autowired
    private ManageUserInfoService manageUserInfoService;

    @Autowired
    private MgNoteService mgNoteService;

    @Autowired
    private AccountTokenService accountTokenService;

    @PostMapping("/login")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "manage用户登录",notes = "manage用户登录")
    @ApiImplicitParam(name = "userInfo", value = "manage用户实体", required = true, dataType = "ManageUserInfoVO")
    public ApiResponse login(@RequestBody ManageUserInfoVO userInfo) throws UnsupportedEncodingException {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if(userInfo != null){
                if(CommonConf.LOGIN_TYPE_PASSWORD.equals(userInfo.getLoginType())){
                    String password = userInfo.getUserPassword();
                    ManageUserInfo existUserInfo = manageUserInfoService.loginByInfo(userInfo);
                    if(existUserInfo != null){
                        if(password.equals(existUserInfo.getUserPassword())){
                            String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()));
                            existUserInfo.setToken(token);
                            accountTokenService.saveToken(existUserInfo, CommonConf.MANAGE_FLAG);
                            existUserInfo.setUserId(null);
                            existUserInfo.setUserPassword(null);
                            apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
                        }else {
                            apiResponse = ApiResponseUtil.getApiResponse(-101,"密码不正确！");
                        }
                    }else apiResponse = ApiResponseUtil.getApiResponse(-101,"该账号未注册！");
                }else if(CommonConf.LOGIN_TYPE_MESSAGE.equals(userInfo.getLoginType())){
                    HashMap<String,String> checkResult = mgNoteService.checkAuthCode(userInfo.getUserMobile(),userInfo.getAuthCode(),CommonConf.MANAGE_FLAG);
                    if(checkResult != null){
                        if("200".equals(checkResult.get("code"))){
                            ManageUserInfo existUserInfo = manageUserInfoService.loginByInfo(userInfo);
                            if(existUserInfo != null){
                                String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()));
                                existUserInfo.setToken(token);
                                accountTokenService.saveToken(existUserInfo,CommonConf.MANAGE_FLAG);
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return apiResponse;
    }

}
