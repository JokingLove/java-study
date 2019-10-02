package com.baomidou.mybatisplus.samples.crud.spider.service.impl;

import com.baomidou.mybatisplus.samples.crud.spider.entity.StockPrice;
import com.baomidou.mybatisplus.samples.crud.spider.mapper.StockPriceMapper;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 股票价格记录表 服务实现类
 * </p>
 *
 * @author JOKING
 * @since 2019-10-01
 */
@Service
public class StockPriceServiceImpl extends ServiceImpl<StockPriceMapper, StockPrice> implements IStockPriceService {

}
