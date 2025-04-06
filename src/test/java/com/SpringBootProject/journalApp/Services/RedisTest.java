package com.SpringBootProject.journalApp.Services;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {


    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testredisTemplate(){
        redisTemplate.opsForValue().set("email","rohannkrote2003@gmail.com");
       Object email = redisTemplate.opsForValue().get("email");
       Object salary = redisTemplate.opsForValue().get("salary");

       int a = 1;
    }



}
