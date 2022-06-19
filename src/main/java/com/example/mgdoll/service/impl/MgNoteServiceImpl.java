package com.example.mgdoll.service.impl;

import com.example.mgdoll.mapper.MgNoteInfoMapper;
import com.example.mgdoll.model.MgNoteInfo;
import com.example.mgdoll.service.MgNoteService;
import com.example.mgdoll.util.ApiResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MgNoteServiceImpl implements MgNoteService {
    private static Logger logger = LoggerFactory.getLogger(MgNoteServiceImpl.class);
    //验证码超过30分钟失效
    private static final int DIFF_DATE = 60*60*1000;

    @Autowired
    private MgNoteInfoMapper mgNoteInfoMapper;

    @Override
    public void insert(MgNoteInfo mgNoteInfo) {
        mgNoteInfoMapper.insert(mgNoteInfo);
    }

    @Override
    public Date getLastInsertTimeByMobile(String userMobile, String authCode, String flag) {
        List<Date> datas = mgNoteInfoMapper.getLastInsertTimeByMobile(userMobile,authCode,flag);
        if(datas != null && datas.size()>0){
            Date data = datas.get(0);
            return data;
        }
        return null;
    }

    @Override
    public HashMap<String, String> checkAuthCode(String userMobile, String authCode, String flag) {
        HashMap<String, String> result = new HashMap<>();
        Date insertTime = this.getLastInsertTimeByMobile(userMobile,authCode,flag);
        if(insertTime != null){
            Long diff = System.currentTimeMillis()-insertTime.getTime();
            logger.info("该验证码接收时间为：{}，距离当前时间：{}ms",insertTime,System.currentTimeMillis()-insertTime.getTime());
            if(diff >= 0 && diff <= DIFF_DATE){
                result.put("code","200");
            }else {
                result.put("code","-1");
                result.put("message","验证码失效！");
            }
        }else {
            result.put("code","-1");
            result.put("message","手机号或验证码不存在！");
        }
        return result;
    }
}
