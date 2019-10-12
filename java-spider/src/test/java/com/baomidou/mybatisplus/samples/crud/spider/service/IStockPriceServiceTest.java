package com.baomidou.mybatisplus.samples.crud.spider.service;

import com.baomidou.mybatisplus.samples.crud.BaseSpringBootJunit;
import com.baomidou.mybatisplus.samples.crud.framework.mp.parser.ShadingTableNameHolder;
import com.baomidou.mybatisplus.samples.crud.spider.entity.StockPrice;
import org.junit.Test;

import javax.annotation.Resource;

public class IStockPriceServiceTest extends BaseSpringBootJunit {

    @Resource
    private IStockPriceService stockPriceService;

    @Test
    public void testSelect() {
        ShadingTableNameHolder.setTableNameParams("0","2019");
        stockPriceService.lambdaQuery()
                .eq(StockPrice::getStockCode, "600012")
                .list()
                .forEach(System.out::println);
    }
}
