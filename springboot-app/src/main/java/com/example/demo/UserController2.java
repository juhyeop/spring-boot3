package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.List;
import java.util.Map;

@RestController
public class UserController2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate redis;

    // /red, get요청시 배경화면 red인 html이 보이게!!
    @GetMapping("/")
    public String hello2() {
        return "<body bgcolor=red>changed------!!!!!!</body>";
    }

//table생성
//member(id, pw)
//select id form member;

   @GetMapping("/mysql2")
    public String dbTest2() {
        try {

            String sql = "SELECT id, pw FROM member";
            List<Map<String, Object>> members = jdbcTemplate.queryForList(sql);
            return "member : " + members;
        } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed! Error: " + e.getMessage();
        }
    }


    @GetMapping("/redis-set")
    public String redisSet() {
        try {
            redis.opsForValue().set("key", "100");
            return "Redis SET OK. key=key, value=100";
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis SET failed! Error: " + e.getMessage();
        }
    }



    @GetMapping("/redis-get")
    public String redisGet() {
        try {
            String value = redis.opsForValue().get("key");
            return "Redis GET OK. key=key >> " + value;
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis GET failed! Error: " + e.getMessage();
        }
    }
}
