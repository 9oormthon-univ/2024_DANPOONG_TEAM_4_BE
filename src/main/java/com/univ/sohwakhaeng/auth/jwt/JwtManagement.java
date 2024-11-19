package com.univ.sohwakhaeng.auth.jwt;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class JwtManagement {

    private static final String PREFIX = "token list:";
    private final RedisTemplate<String, Object> redisTemplate;

    public JwtManagement(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void deleteToken(String token) {
        redisTemplate.delete(PREFIX + token);
    }
    public void insertToken(String token) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(PREFIX + token, true);
    }

    public boolean isExist(String jwt) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        return ops.get(PREFIX + jwt) != null;
    }
}
