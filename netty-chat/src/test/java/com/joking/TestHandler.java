package com.joking;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LoggingHandler;

public class TestHandler {

    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline()
                .addLast(new LoggingHandler());

        channel.writeInbound("Hello World!");
        channel.writeAndFlush("Hello World!");
    }
}
