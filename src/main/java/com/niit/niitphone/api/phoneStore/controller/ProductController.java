package com.niit.niitphone.api.phoneStore.controller;


import com.niit.niitphone.api.phoneStore.mapper.ProductMapper;
import com.niit.niitphone.api.phoneStore.pojo.Product;
import com.niit.niitphone.pojo.rmso.ResultVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 章卜
 * @since 2020-12-16
 */
@RestController
@RequestMapping("product/")
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/selectAll")
    public ResultVO<List<Product>> selectAll() {
        return new ResultVO<List<Product>>(productMapper.selectList(null));
    }

    @GetMapping("/selectById")
    public ResultVO<Product> selectById(@Param("productId") int productId) {
        return new ResultVO<Product>(productMapper.selectById(productId));
    }

}

