//package com.SpringBootProject.journalApp.Services;
//
//import com.SpringBootProject.journalApp.Entity.UserEntity;
//import com.SpringBootProject.journalApp.repository.UserRepository;
//import com.SpringBootProject.journalApp.services.UserDetailServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import java.util.Collections;
//
//import static org.mockito.Mockito.when;
//
//public class UserDetailServiceImplTests {
//
//
//   @Mock
//   public UserRepository userRepository;
//
//    @InjectMocks
//    public UserDetailServiceImpl userDetailServiceimpl;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//
//
//
//  @Test
//  void loadByUserName(){
//      when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(UserEntity.builder().username("Ram").password("Ram").roles(Collections.singletonList("USER")).build());
//      UserDetails user = userDetailServiceimpl.loadUserByUsername("Ram");
//      Assertions.assertNotNull(user);
//  }
//
//}
