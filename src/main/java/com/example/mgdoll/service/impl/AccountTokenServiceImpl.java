package com.example.mgdoll.service.impl;

import com.example.mgdoll.conf.CommonConf;
import com.example.mgdoll.mapper.AccountTokenMapper;
import com.example.mgdoll.model.AccountToken;
import com.example.mgdoll.model.AppUserInfo;
import com.example.mgdoll.model.ManageUserInfo;
import com.example.mgdoll.service.AccountTokenService;
import com.example.mgdoll.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class AccountTokenServiceImpl implements AccountTokenService {
    private static Logger logger = LoggerFactory.getLogger(AccountTokenServiceImpl.class);
    //超过30分钟失效
    private static final int DIFF_DATE = 60*60*1000;

    @Autowired
    private AccountTokenMapper accountTokenMapper;

    @Override
    public void saveToken(Object object, String flag) {
        if(CommonConf.MANAGE_FLAG.equals(flag)){
            ManageUserInfo manageUserInfo = (ManageUserInfo) object;
            insertTokenByManage(manageUserInfo,flag);
        }else if(CommonConf.APP_FLAG.equals(flag)){
            AppUserInfo appUserInfo = (AppUserInfo) object;
            insertTokenByApp(appUserInfo,flag);
        }
    }

    @Override
    public Boolean verifyExpire(String phone, String token) {
        Date insertTime = this.getLastTimeByPhone(phone,token);
        if(insertTime != null){
            Long diff = System.currentTimeMillis()-insertTime.getTime();
            logger.info("接收时间为：{}，距离当前时间：{}ms",insertTime,System.currentTimeMillis()-insertTime.getTime());
            if(diff >= 0 && diff <= DIFF_DATE){
                return true;
            }else {
                return false;
            }
        }
        return null;
    }

    @Override
    public void updateToken(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        String phone = JwtUtil.getUsername(token);
        Date date = new Date();
        accountTokenMapper.updateToken(phone,token,date);
    }

    @Override
    public void updateTokenTest(String s, Date date) {
        accountTokenMapper.updateTokenTest(s,date);
    }

    @Override
    public boolean checkManage(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        String flag = JwtUtil.getRoleFlag(token);
        if(CommonConf.MANAGE_ACTION.equals(flag)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAPP(HttpServletRequest request) {
        String token = request.getHeader("access_token");
        String flag = JwtUtil.getRoleFlag(token);
        if(CommonConf.APP_ACTION.equals(flag)){
            return true;
        }
        return false;
    }

    private Date getLastTimeByPhone(String phone, String token) {
        List<Date> datas = accountTokenMapper.getLastTimeByPhone(phone,token);
        if(datas != null && datas.size()>0){
            Date data = datas.get(0);
            return data;
        }
        return null;
    }

    private void insertTokenByApp(AppUserInfo userInfo, String flag) {
        AccountToken accountToken = new AccountToken();
        accountToken.setToken(userInfo.getToken());
        accountToken.setPhone(userInfo.getUserMobile());
        accountToken.setUserFlag(flag);
        accountToken.setInsertTime(new Date());
        accountTokenMapper.insert(accountToken);
    }

    private void insertTokenByManage(ManageUserInfo userInfo, String flag) {
        AccountToken accountToken = new AccountToken();
        accountToken.setToken(userInfo.getToken());
        accountToken.setPhone(userInfo.getUserMobile());
        accountToken.setUserFlag(flag);
        accountToken.setInsertTime(new Date());
        accountTokenMapper.insert(accountToken);
    }
}
