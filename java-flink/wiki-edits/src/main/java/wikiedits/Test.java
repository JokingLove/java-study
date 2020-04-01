package wikiedits;

import org.apache.flink.shaded.guava18.com.google.common.net.UrlEscapers;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.util.concurrent.CountDownLatch;

public class Test {


    public static void main(String[] args) {
        String  urlStr = "http://8066011.com/";
        int i = 1000;
        while(true) {
            CountDownLatch countDownLatch = new CountDownLatch(i);
            for (int j = 0; j < i; j++) {
                new Thread(() -> {
                    try {
                        countDownLatch.await();
                        URL url = new URL(urlStr);
                        URLConnection conn = url.openConnection();
                        InputStream inputStream = conn.getInputStream();
                        System.out.println(inputStream.available());
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                countDownLatch.countDown();
            }
        }
    }
}
