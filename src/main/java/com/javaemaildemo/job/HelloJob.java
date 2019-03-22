package com.javaemaildemo.job;

import com.javaemaildemo.service.IEmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ${lcl}
 * @Title: HelloJob
 * @ProjectName javaemaildemo
 * @Description: TODO
 * @date 2019/2/27 002713:13
 */
public class HelloJob implements Job {
    @Autowired
    private IEmailService emailService;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //打印当前的执行时间 例如 2017-11-23 00:00:00
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在的时间是：" + sf.format(date));
        //具体的业务逻辑
        System.out.println("开始任务");
        emailService.sendAttachmentMail("657050518@qq.com", "定时发送时", "下午好");
    }
}