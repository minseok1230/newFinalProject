package com.soccer.common;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class FindPasswordMailService implements MailServiceInter{

	@Autowired
	JavaMailSender emailsender;
	
	private static String ePw;

	@Override
	public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = emailsender.createMimeMessage();
		
		message.addRecipients(RecipientType.TO, to);
		message.setSubject("SoccerMatching 회원가입 이메일 인증");
		
		String msgg = "";
		msgg += "<div style='margin:100px;'>";
		msgg += "<h1> 안녕하세요</h1>";
		msgg += "<h1> 축구 매칭 사이트 SOCCER MATCHING 입니다</h1>";
		msgg += "<br>";
		msgg += "<p>아래 임시비밀번호를 사용하여 로그인 해주세요.<p>";
		msgg += "<p>로그인 후에 비밀번호를 변경해주세요!<p>";
		msgg += "<br>";
		msgg += "<p>항상 당신의 멋진경기를 응원합니다. 감사합니다!<p>";
		msgg += "<br>";
		msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg += "<h3 style='color:blue;'>임시비밀번호입니다.</h3>";
		msgg += "<div style='font-size:130%'>";
		msgg += "CODE : <strong>";
		msgg += ePw + "</strong><div><br/> "; // 메일에 인증번호 넣기
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
		// 보내는 사람의 이메일 주소, 보내는 사람 이름
		message.setFrom(new InternetAddress("soccerMatch1111@gmail.com", "SOCCER MATCHING"));// 보내는 사람

		return message;
	}

	@Override
	public String createKey() {
		StringBuffer key = new StringBuffer();
        Random random = new Random();

        for(int i=0; i<8; i++) {
        	// 0~2 사이의 값을 랜덤하게 받아와 idx에 집어넣습니다.
            int idx = random.nextInt(3);

			// 랜덤하게 idx를 받았으면, 그 값을 switchcase를 통해 또 꼬아버립니다.
			// 숫자와 ASCII 코드를 이용합니다.
            switch (idx) {
                case 0 :
                	// a(97) ~ z(122)
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                	// A(65) ~ Z(90)
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                	// 0 ~ 9
                    key.append(random.nextInt(9));
                    break;
            }
        }
        return key.toString();
	}

	@Override
	public String sendSimpleMessgae(String to) throws Exception {
		ePw = createKey();
		
		MimeMessage message = createMessage(to);
		try {
			emailsender.send(message);
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}
		return ePw;
	}
}


