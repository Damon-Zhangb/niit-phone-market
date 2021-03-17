package com.niit.niitphone.api.phoneStore.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.niitphone.api.phoneStore.mapper.UserMapper;
import com.niit.niitphone.api.phoneStore.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.niit.niitphone.api.phoneStore.pojo.vo.UserAddOrUpdateVo;
import com.niit.niitphone.api.sso.pojo.vo.UserLoginVO;

import com.niit.niitphone.pojo.rmso.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 章卜
 * @since 2020-12-16
 */
public interface UserService extends IService<User> {



    /**
     * 根据姓名查询用户
     *
     * @param username
     * @return SysUserEntity
     * @throws Exception
     */
    User selectUserByName(UserLoginVO username) throws Exception;

    /**
     * 添加数据
     *
     * @param addOrUpdateVo
     * @return ResultVO
     * @throws Exception
     */
    ResultVO addDate(UserAddOrUpdateVo addOrUpdateVo) throws Exception;

}
