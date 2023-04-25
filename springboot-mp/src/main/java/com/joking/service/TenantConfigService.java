package com.joking.service;

import org.springframework.stereotype.Service;
import com.joking.entity.TenantConfig;

import java.util.Arrays;
import java.util.List;

/**
 * @author joking
 */
@Service
public class TenantConfigService {

    final String URL = "jdbc:mysql://pre-cluster2-rw-mysql.lepudigital.com:33185/lepu_market?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
    final String USERNAME = "dev";
    final String PASSWORD = "Devu3er-";

    /**
     * 查询数据库返回所有的租户数据库信息
     * @return {@link List<TenantConfig>}
     */
    public List<TenantConfig> getAllTenantConfig() {
        /*
         *           url: jdbc:mysql://pre-cluster2-rw-mysql.lepudigital.com:33185/lepu_market?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true
         *           username: dev
         *           password: Devu3er-
         */
        return Arrays.asList(
                new TenantConfig("tenantDs1", null, URL, USERNAME, PASSWORD),
                new TenantConfig("tenantDs2", null, URL, USERNAME, PASSWORD),
                new TenantConfig("tenantDs3", null, URL, USERNAME, PASSWORD),
                new TenantConfig("tenantDs4", null, URL, USERNAME, PASSWORD),
                new TenantConfig("tenantDs5", null, URL, USERNAME, PASSWORD)
        );
    }

}
