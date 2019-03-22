package com.javaemaildemo.service;

import java.util.List;

public interface IEmailService {
    /**
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容<br>
     * @return boolean 成功返回true，失败返回false
     * @Description 发送简单纯文字邮件
     */
    boolean sendAttachmentMail(String to, String subject, String content);

    /**
     * @param to       收件人地址
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param filepath 包含附件路径地址的字符串数组
     * @return boolean 功返回true，失败返回false
     * @Description 发送带单个或多个附件的邮件
     */
    boolean sendAttachmentMail(String to, String subject, String content, List filepath);
}
