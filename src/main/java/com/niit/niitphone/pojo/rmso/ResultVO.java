package com.niit.niitphone.pojo.rmso;

import java.io.Serializable;

/**
 * 响应消息支持对象
 * @author 章卜
 * @since 2020-12-16
 */
public class ResultVO<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    /**
     * 调用成功时返回
     *
     * @param data
     */
    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    /**
     * 调用失败时返回
     *
     * @param resultCode
     * @param msg
     */
    public ResultVO(ResultCode resultCode, String msg) {
        this.code = resultCode.getCode();
        this.msg = msg;
    }

    /**
     * ignore
     *
     * @param resultCode rc
     */
    public ResultVO(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    private ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.data = data;
    }

    public static ResultVO error(String message) {
        return new ResultVO(ResultCode.FAILED, message);
    }

    public static ResultVO ok() {
        return new ResultVO(ResultCode.SUCCESS);
    }

    public ResultVO<T> data(T data) {
        setData(data);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
