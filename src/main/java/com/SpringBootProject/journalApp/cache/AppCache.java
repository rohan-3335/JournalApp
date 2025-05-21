package com.SpringBootProject.journalApp.cache;

import com.SpringBootProject.journalApp.Entity.ConfigJournalAppEntity;
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
    private ConfigJournalRepository configJournalRepository;

    // Specify RedisTemplate with generics
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Map<String, String> APP_CACHE;

    @PostConstruct
    public void init() {
        try {
            // Initialize cache from DB
            APP_CACHE = new HashMap<>();
            List<ConfigJournalAppEntity> all = configJournalRepository.findAll();
            for (ConfigJournalAppEntity entity : all) {
                APP_CACHE.put(entity.getKey(), entity.getValue());
            }

            // Test Redis connection
            redisTemplate.opsForValue().set("test-key", "connected");
            String value = redisTemplate.opsForValue().get("test-key");
            System.out.println("Redis test value: " + value);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;  // Re-throw so Spring knows initialization failed
        }
    }
}
