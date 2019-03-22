package com.javaemaildemo.service.impl;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.javaemaildemo.service.IEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    //从application.properties配置文件中注入发送者的邮件地址
    @Value("${spring.mail.username}")
    private String fromEmail;

    //注入spring发送邮件的对象
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 简单邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容<br>
     * @return
     */
    @Override
    public boolean sendAttachmentMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);        //执行发送
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 带附件的邮件
     *
     * @param to       收件人地址
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filepath 包含附件路径地址的字符串数组
     * @return
     */
    @Override
    public boolean sendAttachmentMail(String to, String subject, String content, List filepath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            helper.setFrom(fromEmail);
            if (filepath.size() > 0) {                        //读取附件文件（传入文件路径）
                for (Object string : filepath) {        //遍历文件数组，实现多个附件的添加
                    FileSystemResource file = new FileSystemResource(string.toString());
                    String fileName = file.getFilename();//获取文件名
                    helper.addAttachment(fileName, file);//参数：文件名，文件路径
                }
                try {
                    javaMailSender.send(mimeMessage);        //发送邮件
                } catch (Exception e) {
                    return false;                        //发送出现异常(或者文件路径不对)
                }
                return true;                            //成功发送
            } else {
                return false;                            //没有附件文件（中断发送）
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            //捕获到创建MimeMessageHelper的异常
//			return false;
        }
        return true;
    }

}
