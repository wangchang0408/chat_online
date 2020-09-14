/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.maple.chat.utils;

import androidx.annotation.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author wangchang
 * @since 2020年9月4日
 * @describe 比较判断工具
 */
public class TextUtils {
    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(@Nullable Object str) {
        return (str == null || "".equals(str) || "null".equals(str));
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(@Nullable Object str) {
        return !(str == null || "".equals(str) || "null".equals(str));
    }

    /**
     * 比较是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return false;
        } else {
            if (s1.equals(s2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 比较是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean notEquals(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return true;
        } else {
            if (s1.equals(s2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 强制转换为UTF-8编码
     *
     * @param str
     * @return
     */
    public static String utf8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判空
     *
     * @param str
     * @return
     */
    public static String checkEmpty(String str) {
        if (isEmpty(str)) {
            return "";
        } else {
            return str;
        }
    }

    /**
     * 截取小数点后几位
     *
     * @param num 数字
     * @param n   保留小数点后几位
     * @return
     */
    public static String subStr(Double num, int n) {
        String numStr = Double.toString(num);
        int i = numStr.indexOf(".");
        if (n == 0) {
            return numStr.substring(0, i);
        }
        if (i == -1) {
            return numStr;
        }
        numStr = numStr.substring(0, (i + n + 1) > numStr.length() ? numStr.length() : i + n + 1);
        return numStr;
    }

    public static String subStr(Float num, int n) {
        String numStr = Float.toString(num);
        int i = numStr.indexOf(".");
        if (n == 0) {
            return numStr.substring(0, i);
        }
        if (i == -1) {
            return numStr;
        }
        numStr = numStr.substring(0, (i + n + 1) > numStr.length() ? numStr.length() : i + n + 1);
        return numStr;
    }

    public static String subStr(Integer num, int n) {
        String numStr = Integer.toString(num);
        int i = numStr.indexOf(".");
        if (n == 0) {
            return numStr.substring(0, i);
        }
        if (i == -1) {
            return numStr;
        }
        numStr = numStr.substring(0, (i + n + 1) > numStr.length() ? numStr.length() : i + n + 1);
        return numStr;
    }

}
