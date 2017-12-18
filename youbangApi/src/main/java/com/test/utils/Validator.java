package com.test.utils;

import org.apache.commons.lang.StringUtils;


import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

public class Validator {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((1[0-9]))\\d{9}$";

    /**
     * jiachenghao
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE2 = "^1(3|4|5|7|8)\\d{9}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 验证是否是数字
     */
    public static final String REGEX_NUMBER = "^[0-9]*$";

    /**
     * 汉字或者字母
     */
    public static final String REGEX_CHINESE = "^[a-zA-Z\\u4e00-\\u9fa5]{0,60}$";

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static boolean isChinese(String str) {
        return  Pattern.matches(REGEX_CHINESE, str);
    }

    public static void main(String[] args) {
        String s="Ereee";
        System.out.println(isChinese(s));
    }
    /**
     * jiachenghao
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile2(String mobile) {
        if (!StringValidateUtil.isNotEmpty(mobile)) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /********************验证字符串或者object是否是数字**********************/

    //验证是否是数字类型的字符串
    public static boolean isNumber(String number) {
        if (StringUtils.isEmpty(number))
            return false;
        return Pattern.matches(REGEX_NUMBER, number);
    }

    // 验证是否是数字类型的字符串
    public static boolean isNumber(Object number) {
        if (number == null && StringUtils.isEmpty(number.toString()))
            return false;
        return Pattern.matches(REGEX_NUMBER, number.toString());
    }

    /********************字符串或者object转Long**********************/

    // 验证是否是数字类型  如果不是则返回0
    public static Long object2Long0(Object number) {
        if (number == null || StringUtils.isEmpty(number.toString()) || !Pattern.matches(REGEX_NUMBER, number.toString()))
            return (long) 0;
        return Long.valueOf(number.toString());
    }

    // 验证是否是数字类型  如果不是则返回null
    public static Long object2LongNull(Object number) {
        if (number == null || StringUtils.isEmpty(number.toString()) || !Pattern.matches(REGEX_NUMBER, number.toString()))
            return null;
        return Long.valueOf(number.toString());
    }

    // 验证是否是数字类型  如果不是则返回0
    public static Long string2Long0(String number) {
        if (StringUtils.isEmpty(number) || !Pattern.matches(REGEX_NUMBER, number.toString()))
            return (long) 0;
        return Long.valueOf(number.toString());
    }

    // 验证是否是数字类型  如果不是则返回null
    public static Long string2LongNull(String number) {
        if (StringUtils.isEmpty(number) || !Pattern.matches(REGEX_NUMBER, number.toString()))
            return null;
        return Long.valueOf(number.toString());
    }

    /*************String转double float*******



     public static Float string2Float0(String s) {
     String[] split = s.split("\\.");
     if (split.length == 1) {
     if (isNumber(split[0])) {
     return Float.valueOf(s);
     } else {
     return (float) 0;
     }
     } else if (split.length == 2) {
     if (isNumber(split[0]) && isNumber(split[1])) {
     return Float.valueOf(s);
     } else {
     return (float) 0;
     }
     } else {
     return (float) 0;
     }
     }
     public static Double string2Double0(String s){
     String[] split = s.split("\\.");
     if (split.length == 1) {
     if (isNumber(split[0])) {
     return Double.valueOf(s);
     } else {
     return (double) 0;
     }
     } else if (split.length == 2) {
     if (isNumber(split[0]) && isNumber(split[1])) {
     return Double.valueOf(s);
     } else {
     return (double) 0;
     }
     } else {
     return (double) 0;
     }
     }
     ***/

    /*************判断必要参数是不是为null**********/

    //判断必要参数是不是为null  如果 有空 返回true
    public static boolean paramsHaveNull(Object... args) {
        for (Object object : args) {
            if (object == null)
                return true;
        }
        return false;
    }

    //如果全部都是空  返回true
    public static boolean paramsAllNull(Object... args) {
        for (Object object : args) {
            if (object != null)
                return false;
        }
        return true;
    }

    //校验Long类型是不是空
    public static boolean isEmptyLong(Long number) {
        if (number != null && number != 0) return false;
        return true;
    }

    //校验Integer类型是不是空
    public static boolean isEmptyInteger(Integer number) {
        if (number != null) return false;
        return true;
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }


    //判断时间是否为空
    public boolean isNullEmpty(Date date) {

        if (date == null) return true;

        return false;
    }

    //
    public static boolean isNull(Object object) {
        if (object == null)
            return true;
        if (StringUtils.isEmpty(object.toString()))
            return true;
        return false;
    }

}
