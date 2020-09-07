package com.joking.springbootrocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootRocketMQApplicationTests {

    @Value("${rocketmq.producer.topic}")
    private String topic;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSendMessage() {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> headers = new HashMap<>();
            headers.put("KEYS", "111" + i);
            headers.put("a", String.valueOf(i));
            Message<String> message = new GenericMessage<>("Hello rocketmq for springboot producer " + i, headers);
            rocketMQTemplate.send(topic + ":tagS", message);
        }

    }

}
