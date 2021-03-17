package com.niit.niitphone.api.phoneStore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.niitphone.api.phoneStore.pojo.User;
import com.niit.niitphone.api.phoneStore.mapper.UserMapper;
import com.niit.niitphone.api.phoneStore.pojo.vo.UserAddOrUpdateVo;
import com.niit.niitphone.api.phoneStore.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niit.niitphone.api.sso.pojo.vo.UserLoginVO;

import com.niit.niitphone.pojo.rmso.ResultCode;
import com.niit.niitphone.pojo.rmso.ResultVO;
import com.niit.niitphone.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 章卜
 * @since 2020-12-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
   private UserMapper userMapper;

    @Override
    public User selectUserByName(UserLoginVO user) throws Exception {
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (user.getUserPhone() != null && !"".equals(user.getUserPhone())) {
            qw.eq("user_phone", user.getUserPhone());
        }

        return userMapper.selectOne(qw);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ResultVO addDate(UserAddOrUpdateVo addOrUpdateVo) throws Exception {
        if (addOrUpdateVo != null) {
            QueryWrapper<User> qw = new QueryWrapper<>();
            qw.eq("user_phone", addOrUpdateVo.getUserPhone());
            if(userMapper.selectOne(qw) != null){
                return new ResultVO(ResultCode.VALIDATE_FAILED, "该手机号已注册!");
            }

            if (addOrUpdateVo.getPassword() == null || addOrUpdateVo.getPassword().equals("")) {
                return new ResultVO(ResultCode.VALIDATE_FAILED, "待添加用户密码不能为空！");
            }  else if (addOrUpdateVo.getUserName() == null || addOrUpdateVo.getUserName().equals("")) {
                return new ResultVO(ResultCode.VALIDATE_FAILED, "待添加用户名不能为空！");
            } else if (addOrUpdateVo.getUserPhone() == null || addOrUpdateVo.getUserPhone().equals("")) {
                return new ResultVO(ResultCode.VALIDATE_FAILED, "待添加用户电话不能为空！");
            }  else if (addOrUpdateVo.getUserSex() == null || addOrUpdateVo.getUserSex().equals("")) {
                return new ResultVO(ResultCode.VALIDATE_FAILED, "待添加用户性别不能为空！");
            } else if (addOrUpdateVo.getUserAddress() == null || addOrUpdateVo.getUserAddress().equals("")) {
                return new ResultVO(ResultCode.VALIDATE_FAILED, "待添加用户地址不能为空！");
            }  else if (addOrUpdateVo.getUserAge() == null || addOrUpdateVo.getUserAge().equals("")) {
                return new ResultVO(ResultCode.VALIDATE_FAILED, "待添加用户年龄不能为空！");
            } else if (addOrUpdateVo.getPassword().length() < 6) {
                return new ResultVO(ResultCode.VALIDATE_FAILED, "密码不得小于6位，请重新输入！");
            }  else {
                String salt = UUID.randomUUID().toString();
//                byte[] sb = salt.getBytes();
                String sha256 = SHA256Util.sha256(addOrUpdateVo.getPassword(), null);
                addOrUpdateVo.setPassword(sha256);
//                addOrUpdateVo.setSalt(sb);
                int res = userMapper.addDate(addOrUpdateVo);
                if (res > 0) {
                    return new ResultVO(ResultCode.SUCCESS, "用户注册成功！");
                } else {
                    return new ResultVO(ResultCode.VALIDATE_FAILED, "添加失败！");
                }
            }
        } else {
            return new ResultVO(ResultCode.FAILED, "空参数！");
        }
    }

}
