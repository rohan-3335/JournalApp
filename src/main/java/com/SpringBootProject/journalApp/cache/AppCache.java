package com.SpringBootProject.journalApp.cache;

import com.SpringBootProject.journalApp.Entity.ConfigJournalAppEntity;
import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.repository.ConfigJournalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    public ConfigJournalRepository configJournalRepository;


    public Map<String,String > APP_CACHE;

    @Autowired
    private RedisTemplate redisTemplate;


    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
       List<ConfigJournalAppEntity> all = configJournalRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity : all){
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());

        }


    }
    @PostConstruct
    public void testRedisConnection() {
        redisTemplate.opsForValue().set("test-key", "connected");
        String value = (String) redisTemplate.opsForValue().get("test-key");
        System.out.println("Redis test value: " + value);
    }


}
