package com.niit.niitphone.api.topPhone.controller;


import com.niit.niitphone.api.topPhone.pojo.PhoneName;
import com.niit.niitphone.api.topPhone.pojo.PhoneType;
import com.niit.niitphone.pojo.rmso.ResultVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 章卜
 * @since 2020-12-16
 */
@RestController
@RequestMapping("top")
public class TopController {

  @Autowired
  public RedisTemplate redisTemplate;

  @GetMapping("/getRedis")
  public ResultVO<List<PhoneName>> getRedis() {

    List<PhoneName> list = new ArrayList<>();
      Map<String, Integer> map = redisTemplate.opsForHash().entries("wordCount");

     for (String k : map.keySet()) {
       PhoneName phoneName = new PhoneName(k,map.get(k));
       list.add(phoneName);
     }
    list.sort(new Comparator<PhoneName>() {
      @Override
      public int compare(PhoneName o1, PhoneName o2) {
        return o2.getNumber() - o1.getNumber();
      }
    });
    return new ResultVO<List<PhoneName>>(list);
  }

  @GetMapping("/getType")
  public ResultVO<List<PhoneType>> getType() {

    List<PhoneType> list = new ArrayList<>();
    Map<String, Integer> map = redisTemplate.opsForHash().entries("wordCount1");

    for (String k : map.keySet()) {
      PhoneType phoneType = new PhoneType(k,map.get(k));
      list.add(phoneType);
    }
    list.sort(new Comparator<PhoneType>() {
      @Override
      public int compare(PhoneType o1, PhoneType o2) {
        return o2.getPhoneNum() - o1.getPhoneNum();
      }
    });
    return new ResultVO<List<PhoneType>>(list);
  }

}

