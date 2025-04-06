package com.SpringBootProject.journalApp.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {


    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
    RedisTemplate redisTemplate1 = new RedisTemplate<>();
    redisTemplate1.setConnectionFactory(factory);
    redisTemplate1.setKeySerializer(new StringRedisSerializer());
    redisTemplate1.setValueSerializer(new StringRedisSerializer());

    return redisTemplate1;
    }
}
