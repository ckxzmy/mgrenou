package com.example.mgdoll.task;

import com.example.mgdoll.service.AccountTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 定时任务
 */
@Component
public class ScheduleTask{

    private static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private AccountTokenService accountTokenService;

    @Scheduled(cron = "59 59 23 * * ?")//每天23点59分59秒更新
//    @Scheduled(cron = "59 11 17 * * ?")
    public void updateDownloadNum(){
        try {
//            accountTokenService.updateTokenTest("18015931138",new Date());
            logger.info("updateDownloadNum success!");
        } catch (Exception e) {
            logger.error("updateDownloadNum fail：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
