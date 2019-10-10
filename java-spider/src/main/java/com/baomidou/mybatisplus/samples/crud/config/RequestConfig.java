package com.baomidou.mybatisplus.samples.crud.config;

import com.baomidou.mybatisplus.samples.crud.util.UserAgent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @author joking
 */
@Configuration
public class RequestConfig {

    private static final int CONN_TIMEOUT_IN_MILLIS = 3000;
    private static final int READ_TIMEOUT_IN_MILLIS = 3000;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(CONN_TIMEOUT_IN_MILLIS);
        httpRequestFactory.setReadTimeout(READ_TIMEOUT_IN_MILLIS);

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.setInterceptors(Collections.singletonList(new UserAgentInterceptor()));
        return restTemplate;
    }

    static class UserAgentInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            headers.add(HttpHeaders.USER_AGENT, UserAgent.randomUserAgent());
            return execution.execute(request, body);
        }
    }
}
