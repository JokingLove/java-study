package com.joking.springbootrocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OnewayProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_oneway");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message message = new Message("TopicTest_oneway", "TagC", ("Hello Workd" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(message);
        }

        producer.shutdown();
    }
}
