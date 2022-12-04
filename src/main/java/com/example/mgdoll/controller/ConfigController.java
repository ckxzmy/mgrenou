package com.example.mgdoll.controller;

import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.MgConfigData;
import com.example.mgdoll.service.AccountTokenService;
import com.example.mgdoll.service.ConfigService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import com.example.mgdoll.util.QiNiuUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/config")
public class ConfigController {
    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private QiNiuUtil qiNiuUtil;
    @Autowired
    private ConfigService configService;
    @Autowired
    private AccountTokenService accountTokenService;

    @GetMapping("/getUpToken")
    @CrossOrigin
    public ApiResponse getUpToken(HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        accountTokenService.updateToken(request);
        String upToken = qiNiuUtil.upToken();
        return apiResponse = ApiResponseUtil.getApiResponse(upToken);
    }

    @GetMapping("/getConfigData")
    @CrossOrigin
    @ApiImplicitParam(name = "configName", value = "name", required = true, dataType = "String")
    public ApiResponse getConfigData(HttpServletRequest request, @RequestParam String configName){
        ApiResponse apiResponse = new ApiResponse();
        try {
            if(accountTokenService.checkManage(request)){
                MgConfigData mgConfigData = configService.selectByConfigName(configName);
                apiResponse = ApiResponseUtil.getApiResponse(mgConfigData);
            }else {
                apiResponse = ApiResponseUtil.getApiResponse(-1,"禁止访问");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @PostMapping("/updateConfigData")
    @CrossOrigin
    @ApiImplicitParam(name = "configDate", value = "configDate", dataType = "MgConfigData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "configName", value = "配置名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "configValue", value = "内容", required = true, dataType = "String")
    })
    public ApiResponse updateConfigData(HttpServletRequest request, @RequestBody MgConfigData configData){
        ApiResponse apiResponse = new ApiResponse();
        try {
            if(accountTokenService.checkManage(request)) {
                accountTokenService.updateToken(request);
                String token = request.getHeader("access_token");
                String userId = JwtUtil.getUserId(token);
                Integer existNum = configService.selectExist(configData);
                if (existNum > 0) {
                    configData.setInsertTime(new Date());
                    configData.setInsertBy(userId);
                    configService.updateData(configData);
                    apiResponse = ApiResponseUtil.getApiResponse(configData);
                } else {
                    apiResponse = ApiResponseUtil.getApiResponse(-1, "没有匹配的数据");
                }
            }else {
                apiResponse = ApiResponseUtil.getApiResponse(-1,"禁止访问");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @PostMapping("/insertConfigData")
    @CrossOrigin
    @ApiImplicitParam(name = "configDate", value = "configDate", dataType = "MgConfigData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "configName", value = "配置名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "configValue", value = "内容", required = true, dataType = "String")
    })
    public ApiResponse insertConfigData(HttpServletRequest request, @RequestBody MgConfigData configData){
        ApiResponse apiResponse = new ApiResponse();
        try {
            if(accountTokenService.checkManage(request)) {
                accountTokenService.updateToken(request);
                String token = request.getHeader("access_token");
                String userId = JwtUtil.getUserId(token);
                Integer existNum = configService.selectExist(configData);
                if(existNum < 0){
                    configData.setInsertTime(new Date());
                    configData.setInsertBy(userId);
                    configService.insert(configData);
                    apiResponse = ApiResponseUtil.getApiResponse(configData);
                }else {
                    apiResponse = ApiResponseUtil.getApiResponse(-1,"已存在这个配置");
                }
            }else {
                apiResponse = ApiResponseUtil.getApiResponse(-1,"禁止访问");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

//    @PostMapping("/upload")
//    @CrossOrigin
//    public ApiResponse upload(@RequestParam("file") MultipartFile file) throws IOException {
//
//        String fileName = file.getOriginalFilename();
//        if (fileName == null) {
//            logger.error("传入的文件名不能为空");
//            return ApiResponseUtil.getApiResponse("传入的文件名不能为空");
//        }
//        FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
//        String url = "";
//        url = new QiNiuUtil().upload(fileInputStream);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("fileName", file.getOriginalFilename());
//        map.put("url", url);
//
//        return ApiResponseUtil.getApiResponse(map,1000000,"上传文件成功");
//    }

}
