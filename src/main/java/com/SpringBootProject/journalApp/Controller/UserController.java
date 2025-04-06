package com.SpringBootProject.journalApp.Controller;


import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.api.response.WeatherResponse;
import com.SpringBootProject.journalApp.repository.UserRepository;
import com.SpringBootProject.journalApp.services.UserService;
import com.SpringBootProject.journalApp.services.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

//    @GetMapping
//    public List<UserEntity> getUsers(){
//        return userService.getall();
//    }
//
//    @PostMapping
//    public ResponseEntity<String> saveUser(@RequestBody UserEntity user) {
//        userService.saveUser(user);
//        return ResponseEntity.ok("User registered successfully!");
//    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user){
//        UserEntity userindb = userService.findbyusername(username);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userindb = userService.findbyusername(username);
        if(userindb != null){
            userindb.setUsername(user.getUsername());
            userindb.setPassword(user.getPassword());
            userService.saveNewUser(userindb);
        }
   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    @DeleteMapping()
//    public ResponseEntity<?> deleteByUserId(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        userRepository.deleteByName(authentication.getName());
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @DeleteMapping()
    public ResponseEntity<?> deleteByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        String username = authentication.getName();
        userRepository.deleteByUsername(username); // Ensure method name matches repository
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Autowired
     private WeatherService weatherService;


    @GetMapping()
    public ResponseEntity<?> greeting(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.callApi("Pune");
        String greeting = "";
        if(weatherResponse != null){
          greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike_c() + "°C";

        }
     return new ResponseEntity<>("Hii " + authentication.getName() + greeting,HttpStatus.OK);

    }


}
