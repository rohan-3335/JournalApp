package com.SpringBootProject.journalApp.Services;

import com.SpringBootProject.journalApp.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {


    @Autowired
    private EmailService emailService;

    @Test
    public void testEmailService(){

        emailService.sendEmail("rohanrote074@gmail.com","This is to check weather it is working or not", "Hii, Om this is CBI.");

    }


}
