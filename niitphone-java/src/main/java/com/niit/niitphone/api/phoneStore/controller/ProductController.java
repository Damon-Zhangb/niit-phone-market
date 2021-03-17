package com.niit.niitphone.api.phoneStore.controller;


import com.niit.niitphone.api.phoneStore.mapper.ProductMapper;
import com.niit.niitphone.api.phoneStore.pojo.Product;
import com.niit.niitphone.api.sso.controller.LoginController;
import com.niit.niitphone.pojo.rmso.ResultVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Collections;
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

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    ProductMapper productMapper;

    @GetMapping("/selectAll")
    public ResultVO<List<Product>> selectAll() {
        List<Product> products = productMapper.selectList(null);
        Collections.shuffle(products);
        return new ResultVO<List<Product>>(products);
    }

    @GetMapping("/selectById")
    public ResultVO<Product> selectById(@Param("productId") int productId) {
        return new ResultVO<Product>(productMapper.selectById(productId));
    }

    @PostMapping("/popularPhone")
    public ResultVO popularPhone(@RequestBody Product product) {
        logger.info(product.getProductName()+"\t"+product.getProductClass());
        System.out.println("==========="+product.getProductName());
        return ResultVO.ok();
    }

}

