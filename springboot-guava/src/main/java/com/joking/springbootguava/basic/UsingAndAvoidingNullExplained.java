package com.joking.springbootguava.basic;


import com.google.common.base.Strings;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Objects;

public class UsingAndAvoidingNullExplained {

    public static void main(String[] args) throws Exception {
        ImageToBase64("/Users/joking/Pictures/不错的图片/dahee-son-tV06QVJXVxU-unsplash.jpg");
    }


    private static void ImageToBase64(String imgPath) throws Exception {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(Objects.requireNonNull(data));
        File file = new File("/Users/joking/Desktop/base64");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(encode.getBytes());
        fos.close();
        // 返回Base64编码过的字节数组字符串
        System.out.println("本地图片转换Base64:" + encode);
    }
}
