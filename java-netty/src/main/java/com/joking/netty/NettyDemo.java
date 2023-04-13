package com.joking.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author joking
 */
@Slf4j
public class NettyDemo {

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            new ServerBootstrap()
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
//                            log.debug("socket channel {}", channel);
                            channel.pipeline()
                                    .addLast(new HttpServerCodec())
                                    .addLast(new LoggingHandler())
                                    .addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, HttpRequest request) throws Exception {
                                            System.out.println("来请求了");
                                            System.out.println(request.method());
                                            System.out.println(request.uri());
                                            HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
                                            ctx.channel().writeAndFlush("<h1> Hello Netty </h1>");
//                                            ctx.channel().writeAndFlush(response);
                                        }

                                    });
                        }
                    })
                    .bind(new InetSocketAddress(8080));

            Thread.sleep(10000000);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
