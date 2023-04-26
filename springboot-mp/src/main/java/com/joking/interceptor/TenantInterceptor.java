package com.joking.interceptor;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author joking
 */
@Slf4j
public class TenantInterceptor implements HandlerInterceptor {

    private final static String TENANT_ID = "tenant_id";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String tenantId = request.getHeader(TENANT_ID);
//        log.info("request header tenantId : {}", tenantId);
//        if (StringUtils.hasText(tenantId)) {
//            DynamicDataSourceContextHolder.push("tenantDs" + tenantId);
//        } else {
//            DynamicDataSourceContextHolder.push(null);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        DynamicDataSourceContextHolder.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        DynamicDataSourceContextHolder.clear();
    }
}
