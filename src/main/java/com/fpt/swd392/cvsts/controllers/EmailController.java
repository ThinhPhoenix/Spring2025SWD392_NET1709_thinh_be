package com.fpt.swd392.cvsts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.swd392.cvsts.services.IEmailService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/send")
    public void sendEmail(
        @RequestParam("to") String to,
        @RequestParam("subject") String subject,
        @RequestParam("body") String body,
        @RequestParam(value = "attach", required = false) MultipartFile attachment
    ) {
        if (attachment != null && !attachment.isEmpty()) {
            emailService.sendEmailWithAttachment(to, subject, body, attachment);
        } else {
            emailService.sendEmail(to, subject, body);
        }
    }
}