package com.joking.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author joking
 */
@Slf4j
public class ChatServer {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        LoggingHandler LOGGER_HANDLER = new LoggingHandler();

        try {

            Channel channel = new ServerBootstrap()
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)

                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
//                                    .addLast(LOGGER_HANDLER)
                                    .addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
//                                    .addLast(new IdleStateHandler(5, 0, 0))
//                                    .addLast(new ChannelDuplexHandler() {
//                                        @Override
//                                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//                                            IdleStateEvent event = (IdleStateEvent) evt;
//                                            if (event.state() == IdleState.READER_IDLE) {
//                                                log.info("已经 5s 没有读到数据了！");
//                                            }
//                                        }
//                                    })
//                                    .addLast(new HttpServerCodec())
//                                    .addLast(new SimpleChannelInboundHandler<HttpRequest>() {
//                                        @Override
//                                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest request) throws Exception {
//                                            System.out.println("接收到数据：");
//                                            System.out.println(request);
//                                        }
//                                    });
                                    .addLast(new ChannelInboundHandlerAdapter() {

                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            System.out.println("有客户端连接上来了");
                                        }

                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            System.out.println("接收到数据：");
                                            System.out.println("msg: " + msg);
                                        }

                                        @Override
                                        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                                            System.out.println("channel registered");
                                        }
                                    });
                        }
                    })
                    .bind(8888)
                    .sync()
                    .channel();
            ChannelFuture channelFuture = channel.closeFuture();
            channelFuture.sync();
        } catch (Exception e) {
            log.error("error: {}", e.getMessage(), e);
        }
    }
}
