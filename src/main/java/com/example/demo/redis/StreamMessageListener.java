package com.example.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StreamMessageListener implements StreamListener<String, MapRecord<String, String, String>> {

    static final Logger LOGGER = LoggerFactory.getLogger(StreamMessageListener.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {

        // 消息ID
        RecordId messageId = message.getId();

        // 消息的key和value
        Map<String, String> body = message.getValue();

        LOGGER.info("stream message。messageId={}, stream={}, body={}", messageId, message.getStream(), body);

        // 通过RedisTemplate手动确认消息
        //this.stringRedisTemplate.opsForStream().acknowledge("mystream", message);
    }



}
