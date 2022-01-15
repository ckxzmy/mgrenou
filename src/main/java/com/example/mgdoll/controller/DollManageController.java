package com.example.mgdoll.controller;

import com.alibaba.fastjson.JSON;
import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.model.*;
import com.example.mgdoll.service.MgColorService;
import com.example.mgdoll.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dollManage")
public class DollManageController {

    @Autowired
    private MgColorService colorService;

    @PostMapping("/insert")
    @ResponseBody
    public ApiResponse insert(@RequestBody MgDoll doll){
        ApiResponse apiResponse = new ApiResponse();
        if(doll != null){
            List<MgColorGroup> colorGroupList = doll.getColorList();
            if(colorGroupList != null && colorGroupList.size()>0){
                colorService.insertColor(colorGroupList);
            }
            List<MgPart> partList = doll.getPartList();
        }

        System.out.println(JSON.toJSON(doll));
        apiResponse = ApiResponseUtil.getApiResponse(doll);
        return apiResponse;
    }

    @GetMapping("/query")
    public ApiResponse queryDoll(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
        return apiResponse;
    }
}
