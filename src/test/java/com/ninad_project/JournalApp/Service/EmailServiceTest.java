package com.ninad_project.JournalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void TestSendMail(){
        emailService.SendMail("email@gmail.com",
                "Test Java MAil send",
                "Hi Aap kaise hai");
    }

}
