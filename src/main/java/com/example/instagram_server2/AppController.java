package com.example.instagram_server2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private JdbcTemplate jdbcTemplate; // JdbcTemplate 사용

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
}
