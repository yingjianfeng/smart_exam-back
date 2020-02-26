package com.ecjtu.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ExamApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    public void hset() {
        redisTemplate.opsForHash().put("h1","k1","v1");
    }

}
