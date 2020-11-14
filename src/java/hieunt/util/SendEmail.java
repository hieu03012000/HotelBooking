/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HIEUNGUYEN
 */
public class SendEmail {

    public static void sendText(String email, String code) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com"));
        mailMessage.setSubject("Your activate code");
        mailMessage.setText("Use this code to activate your email:\n" + code);

        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", "hieunttesting@gmail.com", "Hieu1234");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }
}
