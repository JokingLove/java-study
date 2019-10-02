package com.baomidou.mybatisplus.samples.crud.spider.service.impl;

import com.baomidou.mybatisplus.samples.crud.spider.entity.Category;
import com.baomidou.mybatisplus.samples.crud.spider.mapper.CategoryMapper;
import com.baomidou.mybatisplus.samples.crud.spider.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
