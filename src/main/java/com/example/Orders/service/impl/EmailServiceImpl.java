package com.example.Orders.service.impl;

import com.example.Orders.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//@Service
//public class EmailServiceImpl implements EmailService {
//
//    @Autowired
//    JavaMailSender javaMailSender;
//
//    @Value("${spring.mail.username}")
//    private String sender;
//
//
//    @Override
//    public void sendSimpleMessage(String to, String subject) {
//        try {
//
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setFrom(sender);
//            mailMessage.setTo(to);
//            String text ="Your Order has been placed from ShopKart, Keep Shopping";
//            mailMessage.setText(text);
//            mailMessage.setSubject(subject);
//            javaMailSender.send(mailMessage);
//        }
//
//        catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//}
