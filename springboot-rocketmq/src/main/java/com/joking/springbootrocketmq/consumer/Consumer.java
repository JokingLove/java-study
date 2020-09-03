package com.joking.springbootrocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Consumer {

    public static void main(String[] args) throws MQClientException {
        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_push_group");

        consumer.setNamesrvAddr("localhost:9876");

        consumer.subscribe("TopicTest1234", MessageSelector.bySql("a >= 5"));
//        consumer.subscribe("TopicTest", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.printf("%s Receive New Message: %s %n", Thread.currentThread().getName(), list);
                byte[] body = list.get(0).getBody();
                try {
                    System.out.printf("body: %s %n", new String(body, RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 标记该消息 已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 启动消费者实例
        consumer.start();
        System.out.printf("Consumer Started.%n");

    }
}
