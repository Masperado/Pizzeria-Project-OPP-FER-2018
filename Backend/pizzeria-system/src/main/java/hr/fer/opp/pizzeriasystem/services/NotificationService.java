package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.PizzeriaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;


    public void sendNotification(PizzeriaMessage pm) throws MailException{
//        SimpleMailMessage mail = new SimpleMailMessage();
//        mail.setTo(pm.getTo());
//        mail.setSubject(pm.getSubject());
//        mail.setText(pm.getText());
//        try {
//            javaMailSender.send(mail);
//        }catch (Exception ignorable) { }
    }
}
