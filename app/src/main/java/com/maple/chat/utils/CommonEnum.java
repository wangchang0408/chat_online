package com.maple.chat.utils;

/**
 * 表名枚举类
 *
 * @author wangchang
 * @date 2020-05-12 18:00:00
 */
public enum CommonEnum {
    TAG_PHONE("tag.phone", "手机相关信息");



    /**
     * 数据集类型
     */
    private String key;

    /**
     * 返回结果描述
     */
    private String value;

    CommonEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
