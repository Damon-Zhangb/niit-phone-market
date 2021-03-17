package com.niit.niitphone;

import com.niit.niitphone.api.phoneStore.mapper.UserMapper;
import com.niit.niitphone.api.phoneStore.pojo.vo.UserAddOrUpdateVo;
import com.niit.niitphone.api.phoneStore.service.UserService;
import com.niit.niitphone.pojo.rmso.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NiitPhoneApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() throws Exception {
        UserAddOrUpdateVo ud = new UserAddOrUpdateVo();
        ud.setPassword("1234567");
        ud.setUserAddress("湖北荆州");
        ud.setUserName("章卜");
        ud.setUserAge(12);
        ud.setUserPhone("17330919732");
        ud.setUserSex("男");
        ResultVO result = userService.addDate(ud);

        System.out.println(result.getMsg());

    }

}
