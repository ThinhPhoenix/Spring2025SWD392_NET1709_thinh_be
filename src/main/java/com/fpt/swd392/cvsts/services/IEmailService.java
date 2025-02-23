package com.fpt.swd392.cvsts.services;

import org.springframework.web.multipart.MultipartFile;

public interface IEmailService {
    void sendEmail(String to, String subject, String body);
    void sendEmailWithAttachment(String to, String subject, String body, MultipartFile attachment);
}