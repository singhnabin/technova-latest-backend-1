package com.pawan.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.Context;

import javax.naming.InitialContext;

@Service
public class ContentBuilderService {

    @Autowired
    private TemplateEngine templateEngine;

    public String sendContent(String message){

        Context context = new Context();
        context.setVariable("message",message);
        return templateEngine.process("MailTemplate.html",context);


    }
}
