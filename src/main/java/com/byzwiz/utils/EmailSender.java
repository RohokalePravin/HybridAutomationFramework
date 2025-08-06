package com.byzwiz.utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.util.Properties;

public class EmailSender {

    public static void sendExtentReport(String reportPath) {
        // Sender's email credentials
        final String username = "pravin.rohokale@byzwiz.com";
        final String password = "qndn ruuw ehru buva"; // Use App Password if 2FA is enabled

        // Recipient
        String toEmail = "rohokalep8@gmail.com";

        // SMTP server settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session
        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            }
        );

        try {
            // Compose message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("📊 Automation Test Report");

            // Email body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hi,\n\nPlease find attached the latest automation ExtentReport.\n\nRegards,\nTest Automation");

            // Attach the report
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(reportPath));

            // Combine body and attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Set content
            message.setContent(multipart);

            // Send email
            Transport.send(message);

            System.out.println("✅ ExtentReport emailed successfully!");

        } catch (Exception e) {
            System.out.println("❌ Failed to send email: " + e.getMessage());
        }
    }
}
