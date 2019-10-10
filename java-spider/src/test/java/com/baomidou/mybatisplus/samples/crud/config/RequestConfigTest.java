package com.baomidou.mybatisplus.samples.crud.config;

import com.baomidou.mybatisplus.samples.crud.CrudApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author joking
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class RequestConfigTest {

    @Resource
    private RestTemplate restTemplate;

    @Test
    public void userAgentInterceptorTest() {
        String url = "http://www.baidu.com/";
        int i = 1;
        while(i++ < 5) {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        }
    }
}
