package com.example.mgdoll.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Component
public class QiNiuUtil {
    public static  final String url = "http://测试域名/";  //七牛云图片服务器域名（有效1个月）

    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucket}")
    private String bucket;

    public String upToken(){
        String upToken = "";
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            upToken = auth.uploadToken(bucket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return upToken;
    }

//    public String upload(FileInputStream file){
//        Configuration cfg = new Configuration(Region.huanan());
//        //...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
//            Auth auth = Auth.create("ZaVxlO5lMeEKbHe0EuajPpPp7JGTE1RjXALQuBfD", "Q5OsVgk5rIxsOjo3Bmbsolcng6eTfupQ_U_E5a0p");
//            String upToken = auth.uploadToken("mgdoll-test");
////            path = Constant.domainPicture;
//        try {
//            Response response = uploadManager.put(file, null, upToken, null, null);
//
//            // 解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//        } catch (QiniuException e) {
//            e.printStackTrace();
//        }
////        return path + putRet.key;
//        return null;
//    }
}
