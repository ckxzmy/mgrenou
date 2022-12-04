package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.CommonConf;
import com.example.mgdoll.conf.NotCheckTokenAnn;
import com.example.mgdoll.mapper.AppUserFinanceMapper;
import com.example.mgdoll.model.*;
import com.example.mgdoll.service.*;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import com.example.mgdoll.vo.AppUserInfoVO;
import com.example.mgdoll.vo.RechargeVO;
import com.example.mgdoll.vo.UserSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private AppUserFinanceService financeService;
    @Autowired
    private AppUserRechargeService rechargeService;

    @PostMapping("/login")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "app用户登录",notes = "app用户登录")
    public ApiResponse login(@RequestBody AppUserInfoVO userInfo) throws UnsupportedEncodingException {
        ApiResponse apiResponse = new ApiResponse();
        UserLoginLog loginLog = new UserLoginLog();

        try {
            if(userInfo != null){
                if(CommonConf.LOGIN_TYPE_PASSWORD.equals(userInfo.getLoginType())){
                    String password = userInfo.getUserPassword();
                    AppUserInfo existUserInfo = appUserInfoService.loginByInfo(userInfo);
                    if(existUserInfo != null){
                        if(password.equals(existUserInfo.getUserPassword())){
                            String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()),CommonConf.APP_ACTION);
                            existUserInfo.setToken(token);
                            accountTokenService.saveToken(existUserInfo, CommonConf.APP_FLAG);
                            existUserInfo.setUserId(null);
                            existUserInfo.setUserPassword(null);
                            loginLog.setLoginName(existUserInfo.getUserMobile());
                            loginLog.setLoginType(CommonConf.LOGIN_TYPE_PASSWORD);
                            apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
                        }else {
                            apiResponse = ApiResponseUtil.getApiResponse(-1,"密码不正确！");
                        }
                    }else apiResponse = ApiResponseUtil.getApiResponse(-1,"该账号未注册！");
                }else if(CommonConf.LOGIN_TYPE_MESSAGE.equals(userInfo.getLoginType())){
                    HashMap<String,String> checkResult = mgNoteService.checkAuthCode(userInfo.getUserMobile(),userInfo.getAuthCode(),CommonConf.APP_FLAG);
                    if(checkResult != null){
                        if("200".equals(checkResult.get("code"))){
                            AppUserInfo existUserInfo = appUserInfoService.loginByInfo(userInfo);
                            if(existUserInfo != null){
                                String token = JwtUtil.sign(existUserInfo.getUserMobile(),String.valueOf(existUserInfo.getUserId()),CommonConf.APP_ACTION);
                                existUserInfo.setToken(token);
                                accountTokenService.saveToken(existUserInfo, CommonConf.APP_FLAG);
                                existUserInfo.setUserId(null);
                                existUserInfo.setUserPassword(null);
                                loginLog.setLoginName(existUserInfo.getUserMobile());
                                loginLog.setLoginType(CommonConf.LOGIN_TYPE_MESSAGE);
                                apiResponse = ApiResponseUtil.getApiResponse(existUserInfo);
                            }else apiResponse = ApiResponseUtil.getApiResponse(-1,"该账号未注册！");
                        }else {
                            apiResponse = ApiResponseUtil.getApiResponse(-1,checkResult.get("message"));
                            return apiResponse;
                        }
                    }
                }else {
                    apiResponse = ApiResponseUtil.getApiResponse(-1,"请正确登录！");
                    return apiResponse;
                }

            }
            if(apiResponse.getCode() == 200){
                Date now = new Date();
                loginLog.setLogId(now.getTime());
                loginLog.setLoginPlace(CommonConf.APP_FLAG);
                loginLog.setLoginTime(now);
                appUserInfoService.insertLog(loginLog);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(-1,e.getMessage());
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
                    apiResponse = ApiResponseUtil.getApiResponse(-1,"手机号不存在!");
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
                                    AppUserFinance finance = new AppUserFinance();
                                    finance.setAppUser(userInfo.getUserId());
                                    finance.setBalance(new BigDecimal(0));
                                    finance.setPoint(0);
                                    financeService.insert(finance);

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
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(-1,e.getMessage());
        }
        return apiResponse;
    }

    @PostMapping("/addAppUser")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "新增会员",notes = "新增会员")
    public ApiResponse addAppUser(@RequestBody AppUserInfoVO userInfo, HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            if(userInfo != null){
                Integer existNum = appUserInfoService.selectExistUserByMobile(userInfo);
                if(existNum >0){
                    apiResponse = ApiResponseUtil.getApiResponse(-1,"手机号存在!");
                }else {
                    if(userInfo != null && StringUtils.isNotEmpty(userInfo.getUserMobile()) && StringUtils.isNotEmpty(userInfo.getUserPassword())){
                        userInfo.setUserId(UUID.randomUUID().toString().replace("-",""));
                        userInfo.setInsertTime(new Date());
                        appUserInfoService.insert(userInfo);
//                        userInfo.setUserId(null);
//                        userInfo.setUserPassword(null);
                        apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
                        AppUserFinance finance = new AppUserFinance();
                        finance.setAppUser(userInfo.getUserId());
                        finance.setBalance(userInfo.getBalance());
                        finance.setPoint(userInfo.getPoint());
                        finance.setUpdateTime(new Date());
                        finance.setUpdateBy(userInfo.getUserId());
                        financeService.insert(finance);
                        AppUserRecharge rechargeLog = new AppUserRecharge();
                        rechargeLog.setFinanceId(finance.getId());
                        rechargeLog.setUpdateTime(new Date());
                        rechargeLog.setUpdateBy(userInfo.getUserId());
                        rechargeLog.setAmount(userInfo.getBalance());
                        rechargeService.insert(rechargeLog);
                        logger.info("新增成功");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(-1,e.getMessage());
        }
        return apiResponse;
    }

    @PostMapping("/updateAppUser")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "编辑会员",notes = "编辑会员")
    public ApiResponse updateAppUser(@RequestBody AppUserInfoVO userInfo, HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            if(userInfo != null){
                Integer existNum = appUserInfoService.selectExistUserByMobile(userInfo);
                if(existNum >1){
                    apiResponse = ApiResponseUtil.getApiResponse(-1,"手机号已存在!");
                }else {
                    if(userInfo != null){
                        AppUserInfo appUserInfo = appUserInfoService.selectExistUser(userInfo);
                        if(appUserInfo != null){
                            appUserInfoService.update(userInfo);
                            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
                            logger.info("修改成功");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(-1,e.getMessage());
        }
        return apiResponse;
    }

    @PostMapping("/deletaAppUser")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "删除会员",notes = "删除会员")
    public ApiResponse deletaAppUser(@RequestBody AppUserInfoVO userInfo, HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            if(userInfo != null){
                AppUserInfo appUserInfo = appUserInfoService.selectExistUser(userInfo);
                if(appUserInfo != null){
                    userInfo.setStatus(-1);
                    appUserInfoService.update(userInfo);
                    apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
                    logger.info("修改成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(-1,e.getMessage());
        }
        return apiResponse;
    }

    @PostMapping("/rechargeAppUser")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "会员充值",notes = "会员充值")
    @ApiImplicitParam(name = "rechargeVO", value = "rechargeVO", required = true, dataType = "RechargeVO")
    public ApiResponse rechargeAppUser(@RequestBody RechargeVO rechargeVO, HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            String token = request.getHeader("access_token");
            String userId = JwtUtil.getUserId(token);
            if(rechargeVO != null){
                AppUserInfoVO userInfo = new AppUserInfoVO();
                userInfo.setUserId(rechargeVO.getUserId());
                AppUserInfo appUserInfo = appUserInfoService.selectExistUser(userInfo);
                if(appUserInfo != null){
                    AppUserFinance finance = financeService.selectByAppUserId(rechargeVO.getUserId());
                    if(finance != null){
                        AppUserRecharge rechargeLog = new AppUserRecharge();
                        finance.setUpdateTime(new Date());
                        finance.setUpdateBy(userId);
                        if("add".equals(rechargeVO.getFlag())){
                            finance.setBalance(finance.getBalance().add(rechargeVO.getAmount()));
                            rechargeLog.setAmount(rechargeVO.getAmount());
                        }else if("minus".equals(rechargeVO.getFlag())){
                            finance.setBalance(finance.getBalance().subtract(rechargeVO.getAmount()));
                            rechargeLog.setAmount(new BigDecimal(0).subtract(rechargeVO.getAmount()));
                        }
                        financeService.recharge(finance);
                        rechargeLog.setUpdateBy(userId);
                        rechargeLog.setUpdateTime(new Date());
                        rechargeLog.setFinanceId(finance.getId());
                        rechargeService.insert(rechargeLog);
                    }else {
                        finance = new AppUserFinance();
                        if("add".equals(rechargeVO.getFlag())){
                            finance.setAppUser(rechargeVO.getUserId());
                            finance.setBalance(rechargeVO.getAmount());
                            finance.setPoint(0);
                            finance.setUpdateBy(userId);
                            finance.setUpdateTime(new Date());
                            financeService.insert(finance);
                            AppUserRecharge rechargeLog = new AppUserRecharge();
                            rechargeLog.setFinanceId(finance.getId());
                            rechargeLog.setUpdateTime(new Date());
                            rechargeLog.setUpdateBy(userId);
                            rechargeLog.setAmount(rechargeVO.getAmount());
                            rechargeService.insert(rechargeLog);
                        }

                    }

                    apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
                    logger.info("修改成功");
                }else {
                    apiResponse = ApiResponseUtil.getApiResponse(-1,"用户不存在");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(e.getMessage());
        }
        return apiResponse;
    }

    @PostMapping("/searchAppUser")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "会员查询",notes = "会员查询")
    @ApiImplicitParam(name = "userSearchVO", value = "userSearchVO", required = true, dataType = "UserSearchVO")
    public ApiResponse searchAppUser(@RequestBody UserSearchVO userSearchVO, HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            accountTokenService.updateToken(request);
            if(userSearchVO != null){
                if(StringUtils.isNotBlank(userSearchVO.getPetName())){
                    userSearchVO.setPetName("%" + userSearchVO.getPetName() + "%");
                }
                if(StringUtils.isNotBlank(userSearchVO.getStartDateStr())){
                    userSearchVO.setStartDate(sdf.parse(userSearchVO.getStartDateStr()));
                }
                if(StringUtils.isNotBlank(userSearchVO.getEndDateStr())){
                    userSearchVO.setEndDate(sdf.parse(userSearchVO.getEndDateStr()));
                }
                List<AppUserInfo> appUserList = appUserInfoService.queryBySearchCondition(userSearchVO);
                apiResponse = ApiResponseUtil.getApiResponse(appUserList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(-1,e.getMessage());
        }
        return apiResponse;
    }


}
