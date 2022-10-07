package com.example.mgdoll.util;

import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class QiNiuUtil {
    public static  final String url = "http://测试域名/";  //七牛云图片服务器域名（有效1个月）

    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.accessSecretKey}")
    private String accessSecretKey;
    @Value("${qiniu.bucket}")
    private String bucket;

    public String upToken(){
        String upToken = "";
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, accessSecretKey);
            upToken = auth.uploadToken(bucket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return upToken;
    }
}
