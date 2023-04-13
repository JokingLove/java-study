package com.joking.netty.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author joking
 */
public class UDPServer {

    public static void main(String[] args) throws IOException {
        new UDPServer().receive();
    }

    public void receive() throws IOException {
        // 获取 udp 数据通道
        DatagramChannel datagramChannel = DatagramChannel.open();

        // 设置为非阻塞模式
        datagramChannel.configureBlocking(false);

        // 绑定监听
        datagramChannel.bind(new InetSocketAddress("10.105.22.58", 9999));

        System.out.println("UDP 服务启动： 10.105.22.58:9999");

        // 开启一个通道选择器
        Selector selector = Selector.open();

        // 注册通道到 选择器中
        datagramChannel.register(selector, SelectionKey.OP_READ);

        // 通过选择器，查询 IO 事件
        while(selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1025);

            // 迭代 IO 事件
            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 可读事件，有数据来
                if(selectionKey.isReadable()) {
                    // 读取 DatagramChannel 数据通道的数据
                    SocketAddress address = datagramChannel.receive(byteBuffer);

                    byteBuffer.flip();
                    System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));

                    byteBuffer.clear();
                }
            }
            iterator.remove();
        }
        // 关闭选择器和通道
        selector.close();
        datagramChannel.close();
    }
}
