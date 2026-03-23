package com.ty.rentshield.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {
	
	private final JavaMailSender mailSender;

    public EmailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendRentReminder(String toEmail, String tenantName, String propertyAddress) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("Rent Reminder - RentShield");

        message.setText(
                "Hello " + tenantName + ",\n\n"
                + "This is a reminder that your rent for property:\n"
                + propertyAddress
                + "\n\nPlease make the payment before the due date.\n\n"
                + "Regards,\nRentShield System"
        );

        mailSender.send(message);
    }
	
}
