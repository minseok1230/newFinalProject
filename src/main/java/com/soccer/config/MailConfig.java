package com.soccer.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration 
public class MailConfig{

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com"); // smtp 서버 주소
        javaMailSender.setUsername("soccerMatch1111@gmail.com"); // 설정(발신) 메일 아이디
        javaMailSender.setPassword("gbpcfoujodxwrepn"); // 설정(발신) 메일 패스워드
        javaMailSender.setPort(587); //smtp port
        javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보 가져온다.
       
        return javaMailSender;
    }
    
    // SSL/TLS 설정
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp starttls 사용
        properties.setProperty("mail.debug", "true"); // 디버그 사용
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        //properties.setProperty("mail.smtp.ssl.enable", "true");
        
        return properties;
    }
}
