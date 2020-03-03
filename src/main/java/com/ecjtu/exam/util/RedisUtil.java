package com.ecjtu.exam.util;

import com.ecjtu.exam.pojo.People;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void set(People people) throws JsonProcessingException {
        redisTemplate.opsForValue().set(people.getAccount(), new ObjectMapper().writeValueAsString(people));
    }

    public People get(String account) throws JsonProcessingException {
        String json = redisTemplate.opsForValue().get(account);
        if (json == null) {
            return null;
        }
        return new ObjectMapper().readValue(json, People.class);
    }

}
