package com.SpringBootProject.journalApp.Services;

import com.SpringBootProject.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    public UserRepositoryImpl  userRepository;

    @Test
    public  void testSaveNewUsers(){
        Assertions.assertNotNull(userRepository.getUserforSA(),"Passed the testcase");

    }
}
