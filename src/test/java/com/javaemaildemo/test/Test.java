package com.javaemaildemo.test;

import com.javaemaildemo.service.IEmailService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ${lcl}
 * @Title: Test
 * @ProjectName javaemaildemo
 * @Description: TODO
 * @date 2019/2/25 002515:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {
    @Autowired
    private IEmailService emailService;

    @org.junit.Test
    public void sendAttachmentMail() {
        System.out.println("");
    }
}
