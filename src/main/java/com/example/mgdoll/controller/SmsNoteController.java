package com.example.mgdoll.controller;

import com.example.mgdoll.conf.ApiResponseEnum;
import com.example.mgdoll.conf.CommonConf;
import com.example.mgdoll.conf.NotCheckTokenAnn;
import com.example.mgdoll.model.ApiResponse;
import com.example.mgdoll.model.MgNoteInfo;
import com.example.mgdoll.service.MgNoteService;
import com.example.mgdoll.util.ApiResponseUtil;
import com.example.mgdoll.util.SmsUtil;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/sms")
public class SmsNoteController {
    private Logger logger = LoggerFactory.getLogger(SmsNoteController.class);

    @Autowired
    private MgNoteService mgNoteService;

    @GetMapping("/sendSms")
    @NotCheckTokenAnn
    @ResponseBody
    @CrossOrigin
    public ApiResponse sendSms(@RequestParam("userMobile") String mobile,@RequestParam( value = "flag",required = false) String flag){
        ApiResponse apiResponse = new ApiResponse();
        if(StringUtils.isEmpty(flag)){
            apiResponse = ApiResponseUtil.getApiResponse(-101,"系统出错，请联系管理员！");
            return apiResponse;
        }
        if(StringUtils.isNotEmpty(mobile)){
            Random random = new Random();
            try {
                int randomNum = random.nextInt(1000000);
                String codeContext = String.format("%06d", randomNum);
                SendSmsResponse res = SmsUtil.sendSmsP(mobile,codeContext);
                SendStatus[] sendStatus = new SendStatus[0];
                if(res != null){
                    sendStatus = res.getSendStatusSet();
                }
                if(sendStatus != null && sendStatus.length>0){
                    if("Ok".equals(sendStatus[0].getCode())){
                        MgNoteInfo mgNoteInfo = new MgNoteInfo();
                        mgNoteInfo.setNoteValue(codeContext);
                        mgNoteInfo.setSendStatus("success");
                        mgNoteInfo.setInsertTime(new Date());
                        mgNoteInfo.setResContents(SendSmsResponse.toJsonString(res));
                        mgNoteInfo.setUserMobile(mobile);
                        mgNoteInfo.setUserFlag(flag);
                        mgNoteService.insert(mgNoteInfo);
                        apiResponse = ApiResponseUtil.getApiResponse(0,"验证码发送成功！");
                    }else apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.FAIL);
                }else apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.FAIL);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else apiResponse = ApiResponseUtil.getApiResponse(ApiResponseEnum.FAIL);
        return apiResponse;
    }
}
