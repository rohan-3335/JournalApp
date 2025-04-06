package com.SpringBootProject.journalApp.Controller;

import com.SpringBootProject.journalApp.Configuration.SpringSecurityConfig;
import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.cache.AppCache;
import com.SpringBootProject.journalApp.services.UserDetailServiceImpl;
import com.SpringBootProject.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.swing.*;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    public UserService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<String> signup(@RequestBody UserEntity user) {
        userService.saveNewUser(user);
        return ResponseEntity.ok("Done with the process!, For confirmation check Console");
    }
    @Autowired
    public AppCache appCache;

    @Autowired
    public UserDetailServiceImpl userDetailService;

//  @Autowired
//  public AuthenticationManager authenticationManager;
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserEntity user){
//        try{
//       authenticationManager.authenticate(new
//               UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
//            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
//        }
//        catch (Exception e){
//
//        }
//    }



    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
      appCache.init();
    }
}
