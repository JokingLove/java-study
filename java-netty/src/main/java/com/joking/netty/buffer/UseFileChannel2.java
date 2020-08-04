package com.joking.netty.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author joking
 */
public class UseFileChannel2 {
    public static void main(String[] args) {
        nioCopyResourceFile();
    }

    private static void nioCopyResourceFile() {
        long st = System.currentTimeMillis();
        String resourceFile = "/Users/joking/Desktop/test.xls";
        String destFile = "/Users/joking/Desktop/test1.xls";

        File rFile = new File(resourceFile);
        File dFile = new File(destFile);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(rFile);
            fos = new FileOutputStream(dFile);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int rLen = -1;
            while((rLen = inChannel.read(buffer)) != -1) {
                // 转换成写模式
                buffer.flip();
                int outLen = 0;
                while((outLen = outChannel.write(buffer)) != 0) {
                    System.out.println("写入的字节数" + outLen);
                }
                // 转成读模式
                buffer.clear();
            }
            // 强制刷新磁盘
            outChannel.force(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                inChannel.close();
                outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long et = System.currentTimeMillis();
        System.out.println("数据复制完毕，耗时：" + (et - st) / 1000 + "s");
    }
}
