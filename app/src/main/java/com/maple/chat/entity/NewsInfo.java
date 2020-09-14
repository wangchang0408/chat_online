package com.maple.chat.entity;
import lombok.Data;


/**
 * 消息所包含内容
 *
 * @author wangchang
 * @since 2019/4/7 下午12:07
 */
@Data
public class NewsInfo {
    private String headImage;//头像
    private String headName;//名字
    private String headMsg;//消息内容
    private String headDate;//消息时间
}
