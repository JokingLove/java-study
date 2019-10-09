package com.baomidou.mybatisplus.samples.crud.spider.service.impl;

import com.baomidou.mybatisplus.samples.crud.spider.entity.Stock;
import com.baomidou.mybatisplus.samples.crud.spider.mapper.StockMapper;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JOKING
 * @since 2019-10-01
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}
