package com.ninad_project.JournalApp.Service;

import com.ninad_project.JournalApp.Entity.User;
import com.ninad_project.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUsername(User user){
        userService.saveNewUser(user);
    }


    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "12,2,14",
            "3,3,9"
    })
    public void test(int a , int b , int expected ){
        assertEquals(expected , a + b);
    }
}
