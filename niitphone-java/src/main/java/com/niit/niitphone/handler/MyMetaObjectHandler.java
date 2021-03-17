package com.niit.niitphone.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author 章卜
 * @since 2020-12-16
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 自动填充新建时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
//        this.setFieldValByName("createTime", LocalDateTime.now().toString(),metaObject);
//        this.setFieldValByName("updateTime",LocalDateTime.now().toString(),metaObject);
    }

    /**
     * 自动填充更新时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
//        this.setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
