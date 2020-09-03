package com.joking.springbootrocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class ScheduleMessageProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_schedule");

        producer.setNamesrvAddr("localhost:9876");

        producer.start();
        int totalMessagesSend = 100;

        for (int i = 0; i < totalMessagesSend; i++) {
            Message message = new Message("TopicTest_schedule", ("Hello scheduled message " + i).getBytes());
            message.setDelayTimeLevel(3);

            producer.send(message);
        }
        producer.shutdown();
    }
}
