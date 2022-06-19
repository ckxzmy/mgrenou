package com.example.mgdoll.controller;

import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.QiNiuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/config")
public class ConfigController {
    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private QiNiuUtil qiNiuUtil;

    @GetMapping("/getUpToken")
    @CrossOrigin
    public ApiResponse getUpToken(HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        String upToken = qiNiuUtil.upToken();
        return apiResponse = ApiResponseUtil.getApiResponse(upToken);
    }
}
