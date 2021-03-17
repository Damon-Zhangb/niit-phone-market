package com.niit.niitphone.api.phoneStore.service.impl;

import com.niit.niitphone.api.phoneStore.pojo.Order;
import com.niit.niitphone.api.phoneStore.mapper.OrderMapper;
import com.niit.niitphone.api.phoneStore.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
