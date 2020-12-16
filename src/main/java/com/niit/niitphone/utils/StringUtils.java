package com.niit.niitphone.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 章卜
 * @since 2020-12-16
 */
public class StringUtils {
    private StringUtils() {

    }


    /**
     * 手机号校验，只适用于中国大陆手机号
     *
     * @param phone
     * @return boolean
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-09-11 22:48:38
     * @version 1.0
     */
    public static boolean isPhone(String phone) {

        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {

            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();

            return isMatch;
        }
    }
}
