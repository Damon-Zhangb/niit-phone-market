package com.niit.niitphone.api.phoneStore.mapper;

import com.niit.niitphone.api.phoneStore.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niit.niitphone.api.phoneStore.pojo.vo.UserAddOrUpdateVo;
import org.apache.ibatis.annotations.*;
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
public interface UserMapper extends BaseMapper<User> {


    @Insert("insert into NIIT.user (user_id,password,user_name,user_sex,user_phone,user_age,user_address) values(null,#{addOrUpdateVo.password},#{addOrUpdateVo.userName},#{addOrUpdateVo.userSex},#{addOrUpdateVo.userPhone},#{addOrUpdateVo.userAge},#{addOrUpdateVo.userAddress})")
    int addDate(@Param("addOrUpdateVo") UserAddOrUpdateVo addOrUpdateVo);

}
