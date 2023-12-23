package arki.springboot.sb1102email.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    //简单邮件
    public void sendSimpleEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("test-I am testing email sending function through springboot");
        message.setText("successfully");
        message.setTo("1090409167@qq.com");
        message.setFrom("betaweiwang@gmail.com");
        mailSender.send(message);
    }

    //复杂邮件，发送了 html 以及图片文件
    public void sendComplexMail(String to, String subject, String body, String fileToAttach){
        MimeMessagePreparator preparator = mimeMessage -> {

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(new InternetAddress(to));
            message.setFrom(new InternetAddress("betaweiwang@gmail.com"));
            message.setSubject(subject);
            message.setText(body, true);

            FileSystemResource file = new FileSystemResource(new File(fileToAttach));
            message.addAttachment("ProfiePic.jpeg", file);
        };



        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

}
