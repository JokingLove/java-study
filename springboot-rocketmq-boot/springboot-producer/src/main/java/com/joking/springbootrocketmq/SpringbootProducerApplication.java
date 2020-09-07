package com.joking.springbootrocketmq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class SpringbootProducerApplication implements CommandLineRunner {

    @Value("${rocketmq.producer.topic}")
    private String topic;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        rocketMQTemplate.asyncSend(topic,
                MessageBuilder.withPayload("Hello World! I'm from spring boot message..").build(),
                new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }, 2000);
    }
}
