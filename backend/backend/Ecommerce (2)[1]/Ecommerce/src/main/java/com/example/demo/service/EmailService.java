package com.example.demo.service;



import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.exception.CustomerDataAlreadyFoundException;
@Service
public class EmailService {
	private final JavaMailSender javaMailSender;

    private final CustomerDao customerDao;

    public EmailService(JavaMailSender javaMailSender, CustomerDao customerDao) {
        this.javaMailSender = javaMailSender;
        this.customerDao = customerDao;
    }

   

    @Async
    public void sendWelcomeEmail(String toEmail, String username) {
        String subject = "Welcome to Handloom E-commerce - Your Journey Begins!";
        String message = "Dear, " + username +","+ "\nThankyou for choosing Handloom E-Commerce,where tradition meets convenience. We're thrilled to welcome you to our community of handcrafted wonders. Your journey into the world of exquisite textiles starts now!\r\n"
        		+ "\n"+"\n"+"\nWe're here to make your shopping experience seamless. \r\n"
        				+ "\n"
        				+ "Once again, welcome to Handloom E-Commerce. Happy shopping!"+"\n"+"\n Best regards, "+"\n"+"\n"+"\n The Handloom E-commerce Team. ";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("monikaparasuram@gmail.com"); 

        javaMailSender.send(mailMessage);
    }
    public void checkCustomerData(String username) {
        // Check if customer data already exists
        if (customerDao.existsByUsername(username)) {
            throw new CustomerDataAlreadyFoundException("Customer with username " + username + " already exists.");
        }
}
}



