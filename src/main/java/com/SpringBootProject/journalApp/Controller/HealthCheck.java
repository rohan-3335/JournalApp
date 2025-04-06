package com.SpringBootProject.journalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/Health-Check")
    public String healthcheck(){
        return "OK";
    }
}
