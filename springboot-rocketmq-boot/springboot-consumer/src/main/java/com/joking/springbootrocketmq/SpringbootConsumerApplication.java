package com.joking.springbootrocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SpringbootConsumerApplication {

    @Value("${rocketmq.consumer.topic}")
    private String topic;



    public static void main(String[] args) {
        SpringApplication.run(SpringbootConsumerApplication.class, args);
    }


    @Service
    @RocketMQMessageListener(topic = "topic_springboot", consumerGroup = "consumer-group-springboot",
            selectorType = SelectorType.SQL92,  selectorExpression = "a >= 5")
    public class MyConsumer1 implements RocketMQListener<String> {

        @Override
        public void onMessage(String message) {
            System.out.println(message);
        }
    }
}
