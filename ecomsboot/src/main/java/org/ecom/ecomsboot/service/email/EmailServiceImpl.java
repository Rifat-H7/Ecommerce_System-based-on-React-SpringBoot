package org.ecom.ecomsboot.service.email;

import lombok.RequiredArgsConstructor;

import org.ecom.ecomsboot.model.dto.Mail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;




@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{


    private final JavaMailSender javaMailSender;


    @Override
    @Async
    public void sendCodeByMail(Mail mail) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ninoosmoshi222@gmail.com");
        simpleMailMessage.setTo(mail.getTo());
        simpleMailMessage.setSubject("UserCode Active");
        simpleMailMessage.setText(mail.getCode());
        javaMailSender.send(simpleMailMessage);
    }




}