package com.example.mgdoll.controller;

import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.MgConfigData;
import com.example.mgdoll.service.ConfigService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.QiNiuUtil;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/config")
public class ConfigController {
    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private QiNiuUtil qiNiuUtil;
    @Autowired
    private ConfigService configService;

    @GetMapping("/getUpToken")
    @CrossOrigin
    public ApiResponse getUpToken(HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        String upToken = qiNiuUtil.upToken();
        return apiResponse = ApiResponseUtil.getApiResponse(upToken);
    }

    @GetMapping("/getConfigData")
    @CrossOrigin
    @ApiImplicitParam(name = "configName", value = "name", required = true, dataType = "String")
    public ApiResponse getConfigData(HttpServletRequest request, @RequestParam String configName){
        ApiResponse apiResponse = new ApiResponse();
        try {
            MgConfigData mgConfigData = configService.selectByConfigName(configName);
            apiResponse = ApiResponseUtil.getApiResponse(mgConfigData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

}
