package com.shop.shoppingmall.common.util;

public class Utils {

    public static String toStr(Object str) {
        if (str == null) {
            return "";
        } else {
            return str.toString();
        }
    }

    public static Boolean isNull(Object obj) {
        return obj == null;
    }
}
