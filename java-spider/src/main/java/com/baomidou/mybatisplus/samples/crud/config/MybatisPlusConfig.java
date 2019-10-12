package com.baomidou.mybatisplus.samples.crud.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.samples.crud.framework.mp.parser.IMultiTableNameHandler;
import com.baomidou.mybatisplus.samples.crud.framework.mp.parser.PageInterceptorSimpleShadingTable;
import com.baomidou.mybatisplus.samples.crud.framework.mp.parser.ShadingTableNameHolder;
import com.baomidou.mybatisplus.samples.crud.framework.mp.parser.SimpleShardingTableParser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.*;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
@MapperScan("com.baomidou.mybatisplus.samples.crud.**.mapper")
public class MybatisPlusConfig {

    private static final String TB_STOCK_PRICE = "tb_stock_price";

//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        paginationInterceptor.setLimit(5000);
//        SimpleShardingTableParser simpleShardingTableParser = new SimpleShardingTableParser();
//        Map<String, IMultiTableNameHandler> nameHandlerMap = new HashMap<>(2);
//        nameHandlerMap.put(TB_STOCK_PRICE, (metaObject, sql, tableName) -> {
//            List<Object> tableNameParams = ShadingTableNameHolder.getTableNameParams();
//            if(tableNameParams != null && !tableNameParams.isEmpty()) {
//                List<String> tableNames = new ArrayList<>();
//                for (Object tableNameParam : tableNameParams) {
//                    if(null != tableNameParam) {
//                        String year = String.valueOf(tableNameParam);
//                        if(Integer.parseInt(year) < 2019) {
//                            tableNames.add(TB_STOCK_PRICE);
//                        } else {
//                            tableNames.add(TB_STOCK_PRICE + "_" + year);
//                        }
//                    }
//                }
//                return tableNames;
//            }
//            return Collections.singletonList(TB_STOCK_PRICE + "_" + LocalDate.now().getYear());
//        });
//        simpleShardingTableParser.setTableNameHandlerMap(nameHandlerMap);
//        paginationInterceptor.setSqlParserList(Collections.singletonList(simpleShardingTableParser));
//        return paginationInterceptor;
//    }

    @Bean
    public PageInterceptorSimpleShadingTable paginationInterceptor() {
        PageInterceptorSimpleShadingTable paginationInterceptor = new PageInterceptorSimpleShadingTable();
        paginationInterceptor.setLimit(5000);
        SimpleShardingTableParser simpleShardingTableParser = new SimpleShardingTableParser();
        Map<String, IMultiTableNameHandler> nameHandlerMap = new HashMap<>(2);
        nameHandlerMap.put(TB_STOCK_PRICE, (metaObject, sql, tableName) -> {
            List<Object> tableNameParams = ShadingTableNameHolder.getTableNameParams();
            if(tableNameParams != null && !tableNameParams.isEmpty()) {
                List<String> tableNames = new ArrayList<>();
                for (Object tableNameParam : tableNameParams) {
                    if(null != tableNameParam) {
                        String year = String.valueOf(tableNameParam);
                        if(Integer.parseInt(year) < 2019) {
                            tableNames.add(TB_STOCK_PRICE);
                        } else {
                            tableNames.add(TB_STOCK_PRICE + "_" + year);
                        }
                    }
                }
                return tableNames;
            }
            return Collections.singletonList(TB_STOCK_PRICE + "_" + LocalDate.now().getYear());
        });
        simpleShardingTableParser.setTableNameHandlerMap(nameHandlerMap);
        paginationInterceptor.setSqlParserList(Collections.singletonList(simpleShardingTableParser));
        return paginationInterceptor;
    }
}
