package com.example.instagram_server2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private JdbcTemplate jdbcTemplate; // JdbcTemplate 사용

    @Autowired
    private RedisTemplate<String, String> redisTemplate; // RedisTemplate 사용

    @GetMapping("/")
    public String index() {
        // MySQL 연결 확인
        try {
            jdbcTemplate.execute("SELECT 1"); // MySQL 연결을 확인하기 위한 간단한 쿼리
            return "Successfully connected to MySQL!";
        } catch (Exception e) {
            return "Failed to connect to MySQL: " + e.getMessage();
        }
    }

    @GetMapping("/redis")
    public String checkRedisConnection() {
        // Redis 연결 확인
        try {
            // Redis에 간단한 값을 저장하고 읽어오기
            redisTemplate.opsForValue().set("testKey", "Hello, Redis!");
            String redisValue = redisTemplate.opsForValue().get("testKey");

            // Redis에 접근이 성공하면 값을 읽어온 후, 연결 성공 메시지 반환
            if ("Hello, Redis!".equals(redisValue)) {
                return "Successfully connected to Redis!";
            } else {
                return "Failed to connect to Redis!";
            }
        } catch (Exception e) {
            return "Failed to connect to Redis: " + e.getMessage();
        }
    }
}