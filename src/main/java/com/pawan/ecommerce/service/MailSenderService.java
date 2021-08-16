package com.pawan.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.activation.MimeType;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ContentBuilderService contentBuilderService;


    public void sendMail(String username,String body,String subject){

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("technova@gmail.com");
            mimeMessageHelper.setTo(username);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(contentBuilderService.sendContent(body));
        };
        try{
            javaMailSender.send(mimeMessagePreparator);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
