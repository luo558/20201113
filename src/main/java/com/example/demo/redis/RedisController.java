package com.example.demo.redis;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {


    @GetMapping("getVal")
    public String getVal(){
        return "hello redis";
    }

}
