package com.ecjtu.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

@SpringBootTest
class ExamApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    public void hset() {
        redisTemplate.opsForHash().put("h1","k1","v1");
    }
    @Test
    public void get() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        System.out.println(str.substring(24));
    }

}
