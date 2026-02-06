package com.utility;


import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUtil {

    private static final String FROM_EMAIL = "yourgmail@gmail.com";
    private static final String APP_PASSWORD = "xxxx xxxx xxxx xxxx";

    public static void sendOTP(String toEmail, String otp) throws MessagingException {

        Properties props = new Properties();

        props.put("mail.debug", "true"); // ✅ ADD HERE

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
        );
        message.setSubject("Paynix Password Reset OTP");
        message.setText(
            "Your Paynix OTP is: " + otp +
            "\n\nThis OTP is valid for 5 minutes.\n\nTeam Paynix"
        );

        Transport.send(message);
    }
}
