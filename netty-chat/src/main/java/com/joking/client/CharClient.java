package com.joking.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author joking
 */
@Slf4j
public class CharClient {

    public static void main(String[] args) {

        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        LoggingHandler LOGGER_HANDLER = new LoggingHandler();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(LOGGER_HANDLER)
                                    .addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            System.out.println("已经建立连接");
                                            new Thread(() -> {
                                                Scanner scanner = new Scanner(System.in);
                                                while(true) {
                                                    System.out.println("输入要发送的消息：");
                                                    String s = scanner.nextLine();
                                                    ctx.writeAndFlush(s);
                                                }
                                            }).start();
                                        }
                                    });
                        }
                    });
            bootstrap.connect("127.0.0.1", 8888).sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
