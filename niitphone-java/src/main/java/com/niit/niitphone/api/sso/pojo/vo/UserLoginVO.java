package com.niit.niitphone.api.sso.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求参数
 * @author 章卜
 * @since 2020-12-16
 */
@Data
@ApiModel("登录请求参数")
public class UserLoginVO implements Serializable {

    /**
     * 验证码key
     */
    @ApiParam("验证码key")
    private String token;

    /**
     * 验证码value
     */
    @ApiParam("验证码value")
    private String code;

    /**
     * 手机
     */
    @ApiParam("手机号")
    private String userPhone;

    /**
     * 密码
     */
    private String password;



}
