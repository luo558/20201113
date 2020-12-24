package com.example.demo.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
public class Retry {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("retry")
    public void test() {
        StreamOperations<String, String, String> streamOperations = this.stringRedisTemplate.opsForStream();
        PendingMessagesSummary pendingMessagesSummary = streamOperations.pending("mystream", "group-1");
        assert pendingMessagesSummary != null;
        String minMessageId = pendingMessagesSummary.minMessageId();
        String maxMessageId = pendingMessagesSummary.maxMessageId();
        if (StringUtils.isEmpty(minMessageId)) {
            return;
        }
        List<MapRecord<String, String, String>> result = streamOperations.range("mystream", Range.rightOpen(minMessageId, maxMessageId));
        assert result != null;
        result.forEach(x -> {
            x.forEach(y -> {
                System.out.println(y.getValue());
                streamOperations.acknowledge("group-1", x);
            });
        });
        //把收到的消息进行ack


    }


}
