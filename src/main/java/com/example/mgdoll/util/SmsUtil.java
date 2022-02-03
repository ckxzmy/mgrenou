package com.example.mgdoll.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SmsUtil {

    private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    private static final String SMS_SDK_APP_ID = "1400621427";
    private static final String SIGN_NAME = "木果个人站";
    private static final String TEMPLATE_ID = "1271243";
    private static final String SECRET_ID = "AKID86oGQKyLQEl22vXtymJZZHismOjna6LJ";
    private static final String SECRET_KEY = "iALFsEJ7evvd4E7j48wShfNnwoOilW0E";

    public static SendSmsResponse sendSmsP(String mobile, String codeContext){
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SECRET_ID, SECRET_KEY);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-nanjing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumber = {mobile};
            req.setPhoneNumberSet(phoneNumber);

            req.setSmsSdkAppId(SMS_SDK_APP_ID);
            req.setSignName(SIGN_NAME);
            req.setTemplateId(TEMPLATE_ID);

            String[] templateParamSet1 = {codeContext};
            req.setTemplateParamSet(templateParamSet1);
            logger.info("手机号：{},发送内容：{}",req.getPhoneNumberSet(),req.getTemplateParamSet());

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
            return resp;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
