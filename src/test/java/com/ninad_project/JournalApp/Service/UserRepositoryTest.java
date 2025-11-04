package com.ninad_project.JournalApp.Service;

import com.ninad_project.JournalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void TestSaveNewUser(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}
