package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.mapper.MgMaterialMapper;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.MgDoll;
import com.example.mgdoll.model.MgMaterial;
import com.example.mgdoll.model.MgPart;
import com.example.mgdoll.service.AccountTokenService;
import com.example.mgdoll.service.MgColorService;
import com.example.mgdoll.service.MgDollService;
import com.example.mgdoll.service.MgPartService;
import com.example.mgdoll.util.AESUtil;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("dollApp")
public class DollAppController {
    private Logger logger = LoggerFactory.getLogger(DollAppController.class);

    @Autowired
    private MgDollService dollService;
    @Autowired
    private MgColorService colorService;
    @Autowired
    private MgPartService partService;
    @Autowired
    private AccountTokenService accountTokenService;
    @Autowired
    private MgMaterialMapper materialMapper;


    @GetMapping("/queryDoll")
    @CrossOrigin
    @ApiImplicitParam(name = "dollId", value = "可用10", required = true, dataType = "String")
    public ApiResponse queryDoll(HttpServletRequest request, @RequestParam String dollId){
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
//            final String token = request.getHeader("access_token");
//            String userId = JwtUtil.getUserId(token);
//            if(StringUtils.isNotEmpty(userId)){
                MgDoll mgDoll = new MgDoll();
                List<MgDoll> dollList = dollService.queryDollByCondition(mgDoll);
                apiResponse = ApiResponseUtil.getApiResponse(dollList);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(-1,"查询出错，请联系管理员！");
        }
        return apiResponse;
    }

    @GetMapping("getPicUrl")
    @CrossOrigin  //跨域注解
    @ApiOperation(value = "获取url",notes = "获取url")
    @ApiImplicitParam(name = "modelId", value = "id", required = true, dataType = "String")
    public ApiResponse getPicUrl(HttpServletRequest request,@RequestParam String modelId){
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountTokenService.updateToken(request);
            apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.SUCCESS);
            final String token = request.getHeader("access_token");
            String userId = JwtUtil.getUserId(token);
            MgMaterial mgMaterial = materialMapper.selectByPrimaryKey(Integer.valueOf(modelId));
            if(Optional.ofNullable(mgMaterial).isPresent()){
                String picUrl = AESUtil.encryptAES(mgMaterial.getPicUrl(),AESUtil.getKey("userId"));
//                logger.info("解密后："+ AESUtil.decryptAES(picUrl,AESUtil.getKey("userId")));
                apiResponse = ApiResponseUtil.getApiResponse(picUrl);
            }else {
                apiResponse = ApiResponseUtil.getApiResponse(-1,"未获取到素材，请联系管理员！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = ApiResponseUtil.getApiResponse(-1,"系统错误，请联系管理员！");
        }
        return apiResponse;
    }
}
