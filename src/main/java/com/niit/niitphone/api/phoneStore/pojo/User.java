package com.niit.niitphone.api.phoneStore.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 *
 * </p>
 *
 * @author 章卜
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty("用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名称")
    @TableField(value = "user_name", exist = true)
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty("登录密码")
    @TableField(value = "password", exist = true)
    @JSONField(serialize = false)
    private String password;

    /**
     * 剩余的其他字段
     */
    @ApiModelProperty(value = "顾客性别")
    private String userSex;

    @ApiModelProperty(value = "顾客电话")
    @TableField(value = "user_phone")
    private String userPhone;

    @ApiModelProperty(value = "顾客年龄")
    private Integer userAge;

    @ApiModelProperty(value = "顾客地址")
    private String userAddress;


}
