package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.CommonConf;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.MgWaterMark;
import com.example.mgdoll.service.AccountTokenService;
import com.example.mgdoll.service.WaterMarkService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.JwtUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/waterMark")
public class WaterMarkController {
    private Logger logger = LoggerFactory.getLogger(WaterMarkController.class);

    @Autowired
    private AccountTokenService accountTokenService;
    @Autowired
    private WaterMarkService waterMarkService;

    @PostMapping("/insert")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "新增水印")
    public ApiResponse insertWaterMark(@RequestBody MgWaterMark waterMark, HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
            final String token = request.getHeader("access_token");
            String userId = JwtUtil.getUserId(token);
            if(waterMark != null){
                waterMark.setStatus(CommonConf.WATER_STATUS.pending.getValue());//-1.删除 0.正常 1.审核中(待审) 2.审核不通过
                waterMark.setCreateTime(new Date());
                waterMarkService.insert(waterMark);
                apiResponse = ApiResponseUtil.getApiResponse(waterMark);
            }else apiResponse = ApiResponseUtil.getApiResponse(-1,"数据为空！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @GetMapping("/queryUserWaterList")
    @CrossOrigin
    @ApiOperation(value = "前端用户查询水印")
    public ApiResponse queryUserWaterList(HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
            final String token = request.getHeader("access_token");
            String userId = JwtUtil.getUserId(token);
            if(StringUtils.isNotEmpty(userId)){
                List<MgWaterMark> waterList = waterMarkService.queryWaterListByUserId(userId);
                if(waterList != null){
                    for (MgWaterMark entity: waterList) {
                        entity.setStatusName(CommonConf.WATER_STATUS.getName(entity.getStatus()));
                    }
                }
                apiResponse = ApiResponseUtil.getApiResponse(waterList);
            }else apiResponse = ApiResponseUtil.getApiResponse(-1,"未获取到user，请联系管理员！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @PostMapping("/deleteWater")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "前端用户删除水印")
    public ApiResponse deleteWater(HttpServletRequest request,@RequestBody MgWaterMark waterMark){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
            final String token = request.getHeader("access_token");
            String userId = JwtUtil.getUserId(token);
            if(StringUtils.isNotEmpty(userId)){
                if(userId.equals(waterMark.getUserId())){
                    waterMark.setStatus(CommonConf.WATER_STATUS.delete.getValue());
                    MgWaterMark entity = waterMarkService.updateStatus(waterMark);
                    if(entity != null){
                        entity.setStatusName(CommonConf.WATER_STATUS.getName(entity.getStatus()));
                        apiResponse = ApiResponseUtil.getApiResponse(entity);
                    }else apiResponse = ApiResponseUtil.getApiResponse(-1,"操作失败！");
                }else apiResponse = ApiResponseUtil.getApiResponse(-1,"水印与user不匹配，请联系管理员！");
            }else apiResponse = ApiResponseUtil.getApiResponse(-1,"未获取到user，请联系管理员！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return apiResponse;
    }

    @GetMapping("/queryWaterList")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "后端查询水印列表")
    @ApiImplicitParam(name = "status", value = "-1.删除 0.正常 1.审核中(待审) 2.审核不通过", required = true, dataType = "int")
    public ApiResponse queryWaterList(HttpServletRequest request,@RequestParam Integer status){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
//            final String token = request.getHeader("access_token");
            List<MgWaterMark> list = waterMarkService.queryWaterListByStatus(status);
            apiResponse = ApiResponseUtil.getApiResponse(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return apiResponse;
    }

    @PostMapping("/approveWater")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "审核水印")
    public ApiResponse approveWater(HttpServletRequest request,@RequestBody MgWaterMark waterMark){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
            final String token = request.getHeader("access_token");
            if(waterMark != null){
                MgWaterMark entity = waterMarkService.updateStatus(waterMark);
                if(entity != null){
                    apiResponse = ApiResponseUtil.getApiResponse(entity);
                }else apiResponse = ApiResponseUtil.getApiResponse(-1,"操作失败！");
            }else apiResponse = ApiResponseUtil.getApiResponse(-1,"未获取到水印参数，请联系管理员！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return apiResponse;
    }
}
