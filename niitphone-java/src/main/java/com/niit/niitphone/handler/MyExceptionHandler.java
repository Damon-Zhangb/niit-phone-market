package com.niit.niitphone.handler;

import com.alibaba.fastjson.JSONException;

import com.niit.niitphone.pojo.rmso.ResultVO;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.el.MethodNotFoundException;

/**
 * @author 章卜
 * @since 2020-12-16
 */
@RestControllerAdvice(basePackages = "com.onedata.qsm.api")
public class MyExceptionHandler {

    @ExceptionHandler(value = JSONException.class)
    public ResultVO jsonExceptionHandler(JSONException e) {
        return ResultVO.error("JSON数据转换类型错误" + e.getLocalizedMessage());
    }

    @ExceptionHandler(value = MethodNotFoundException.class)
    public ResultVO methodNotFoundExceptionHandler(MethodNotFoundException e) {
        return ResultVO.error("资源不存在" + e.getLocalizedMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResultVO nullExceptionHandler(NullPointerException e) {
        return ResultVO.error("空指针异常" + e.getLocalizedMessage());
    }


    @ExceptionHandler(value = AuthorizationException.class)
    public ResultVO authorizationExceptionHandler(AuthorizationException e) {
        return ResultVO.error("权限不足，请联系公司管理员授权");
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResultVO httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        return ResultVO.error("不支持的媒体类型" + e.getLocalizedMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandler(Exception e) {
        return ResultVO.error("服务升级维护中，请稍后重试" + e.getLocalizedMessage());
    }


}
