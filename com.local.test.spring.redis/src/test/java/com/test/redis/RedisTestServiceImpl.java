package com.test.redis;


import org.springframework.stereotype.Service;

@Service
public class RedisTestServiceImpl implements RedisTestService {
 
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
 
}