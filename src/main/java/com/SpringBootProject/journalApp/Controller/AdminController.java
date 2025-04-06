package com.SpringBootProject.journalApp.Controller;

import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getUsers() {

        List<UserEntity> all = userService.getall();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>( HttpStatus.FORBIDDEN);
    }

    @PostMapping("/create-admin-users")
    public ResponseEntity<?> addAdmin(@RequestBody UserEntity user){
        userService.saveAdmin(user);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
