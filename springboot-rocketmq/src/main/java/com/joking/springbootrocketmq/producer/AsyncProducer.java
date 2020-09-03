package com.joking.springbootrocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

public class AsyncProducer {

    public static void main(String[] args) throws  Exception {

        DefaultMQProducer producer = new DefaultMQProducer("producer_group_async");

        producer.setNamesrvAddr("localhost:9876");

        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 100;

        final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);

        for (int i = 0; i < messageCount; i++) {
            final int index = i;

            Message msg = new Message("TopicTest_async", "TagB", "OrderId188", "Hello World".getBytes(RemotingHelper.DEFAULT_CHARSET));

            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });

        }

        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.shutdown();

    }
}
