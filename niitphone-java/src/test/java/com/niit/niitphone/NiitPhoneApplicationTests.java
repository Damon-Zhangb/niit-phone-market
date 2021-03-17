package com.niit.niitphone;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
class NiitPhoneApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() throws Exception {

    }

}
