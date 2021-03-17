package com.niit.niitphone.api.phoneStore.service.impl;

import com.niit.niitphone.api.phoneStore.pojo.Product;
import com.niit.niitphone.api.phoneStore.mapper.ProductMapper;
import com.niit.niitphone.api.phoneStore.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 章卜
 * @since 2020-12-16
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
