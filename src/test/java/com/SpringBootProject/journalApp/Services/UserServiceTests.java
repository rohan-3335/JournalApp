//package com.SpringBootProject.journalApp.Services;
//
//import com.SpringBootProject.journalApp.Entity.UserEntity;
//import com.SpringBootProject.journalApp.repository.UserRepository;
//import com.SpringBootProject.journalApp.services.UserService;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTests {
//
//
//    @Autowired
//    public UserRepository userRepository;
//
//    @Autowired
//    public UserService userService;
//
//
//
//    @ParameterizedTest
//    @ValueSource(strings = {"Kushal", "Om"})  // ✅ Corrected syntax
//    public void testFindByName(String name) {  // ✅ Corrected method name
//        assertNotNull(userRepository.findByUsername(name),"Failed for : " + name);
//    }
//
//
//
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentProvider.class)
//    public void testSaveNewUser(UserEntity user){
//
//        assertTrue(userService.saveNewUser(user));
//    }
//}
//
//
