package com.niit.niitphone.api.phoneStore.mapper;

import com.niit.niitphone.api.phoneStore.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 章卜
 * @since 2020-12-16
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
