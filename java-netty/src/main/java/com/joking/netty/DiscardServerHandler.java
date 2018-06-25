package com.joking.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        // discard the received data
        ByteBuf in = (ByteBuf)msg;
        try{
//            while(in.isReadable()) { // (1) 循环遍历拿到的缓存中的数据
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
//            System.out.print(in.toString(CharsetUtil.US_ASCII)); // 代替上面低效的循环

            // ECHO 服务（响应式协议），将接受到的数据返回给客户端
            ctx.write(msg);
            ctx.flush();

        } finally {
//            ReferenceCountUtil.release(msg); // （2）// 当将接受到的buf返回去的时候，不需要手动去释放资源，系统会自动释放
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        // close the connection when an exception is raised
        cause.printStackTrace();
        ctx.close();
    }
}
