package com.example.mgdoll.controller;

import com.alibaba.fastjson.JSON;
import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.mapper.MgDollMapper;
import com.example.mgdoll.model.*;
import com.example.mgdoll.service.AccountTokenService;
import com.example.mgdoll.service.MgColorService;
import com.example.mgdoll.service.MgDollService;
import com.example.mgdoll.service.MgPartService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dollManage")
public class DollManageController {
    private Logger logger = LoggerFactory.getLogger(DollManageController.class);

    @Autowired
    private MgDollService dollService;
    @Autowired
    private MgColorService colorService;
    @Autowired
    private MgPartService partService;
    @Autowired
    private AccountTokenService accountTokenService;

    @PostMapping("/insert")
    @ResponseBody
    @CrossOrigin
    public ApiResponse insert(@RequestBody MgDoll doll,HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            final String token = request.getHeader("access_token");
            String userId = JwtUtil.getUserId(token);
            if(doll != null){
                if(doll.getDollId() == null){
                    doll.setInsertBy(userId);
                    doll.setInsertTime(new Date());
                    doll.setOwnerId(userId);
                    int num = dollService.insert(doll);
                    if(num > 0){
                        List<MgColorGroup> colorGroupList = doll.getColorList();
                        if(colorGroupList != null && colorGroupList.size()>0){
                            colorService.insertColor(colorGroupList,token);
                        }
                        List<MgPart> partList = doll.getPartList();
                        if(partList != null && partList.size()>0){
                            partService.insertPart(partList,token,doll);
                        }
                    }
                }else {
                    int num = dollService.update(doll);
                    List<MgColorGroup> colorGroupList = doll.getColorList();
                    if(colorGroupList != null && colorGroupList.size()>0){
                        colorService.updateColor(colorGroupList,token);
                    }
                    List<MgPart> partList = doll.getPartList();
                    if(partList != null && partList.size()>0){
                        partService.updatePart(partList,token,doll);
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info(JSON.toJSON(doll).toString());
        apiResponse = ApiResponseUtil.getApiResponse(doll);
        return apiResponse;
    }

    @GetMapping("/queryDoll")
    @CrossOrigin
    public ApiResponse queryDoll(HttpServletRequest request,@RequestParam String dollId){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
            if(StringUtils.isNotEmpty(dollId)){
                List<MgPart> partList = partService.queryPartListByDollId(Integer.valueOf(dollId));

                MgDoll mgDoll = dollService.queryByDollId(Integer.valueOf(dollId));
                mgDoll.setPartList(partList);
                apiResponse = ApiResponseUtil.getApiResponse(mgDoll);
            }else apiResponse = ApiResponseUtil.getApiResponse(-1,"dollId为空！");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @GetMapping("/queryDollList")
    @CrossOrigin
    public ApiResponse queryDollList(HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
            final String token = request.getHeader("access_token");
            String userId = JwtUtil.getUserId(token);
            if(StringUtils.isNotEmpty(userId)){
                MgDoll mgDoll = new MgDoll();
                mgDoll.setOwnerId(userId);
                List<MgDoll> dollList = dollService.queryByOwnerId(mgDoll);
                apiResponse = ApiResponseUtil.getApiResponse(dollList);
            }else apiResponse = ApiResponseUtil.getApiResponse(-1,"未获取到user，请联系管理员！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @GetMapping("exportPic")
    @CrossOrigin  //跨域注解
    public void exportPic(HttpServletRequest request,@RequestParam String dollId){
        final String token = request.getHeader("access_token");

    }

}
