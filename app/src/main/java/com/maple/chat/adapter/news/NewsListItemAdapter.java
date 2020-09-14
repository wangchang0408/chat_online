package com.maple.chat.adapter.news;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.maple.chat.R;
import com.maple.chat.entity.NewsInfo;
import com.maple.chat.fragment.news.NewsDetailFragment;
import com.maple.chat.fragment.news.NewsFragment;
import com.scwang.smartrefresh.layout.adapter.SmartRecyclerAdapter;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.xuexiang.xui.utils.ResUtils;

import java.util.List;

/**
 * adapter_news_list_item适配器
 *
 * @author wangchang
 * @since 2020/9/8
 */
public class NewsListItemAdapter extends SmartRecyclerAdapter<NewsInfo> {

    private NewsFragment newsFragment;

    public NewsListItemAdapter() {
        super(R.layout.adapter_news_list_item);
    }

    public NewsListItemAdapter(List<NewsInfo> data, NewsFragment newsFragment) {
        super(data, R.layout.adapter_news_list_item);
        this.newsFragment = newsFragment;
    }


    /**
     * 绑定布局控件
     *
     * @param holder
     * @param newsInfo
     * @param position
     */
    @Override
    protected void onBindViewHolder(SmartViewHolder holder, NewsInfo newsInfo, int position) {
        RelativeLayout relativeLayout = holder.itemView.findViewById(R.id.msg_layout);
        holder.text(R.id.head_image, newsInfo.getHeadImage());
        holder.text(R.id.head_name, newsInfo.getHeadName());
        holder.text(R.id.head_msg, newsInfo.getHeadMsg());
        holder.text(R.id.head_date, newsInfo.getHeadDate());

        //消息详情页面
        relativeLayout.setOnClickListener(view -> {
            newsFragment.openNewPage(NewsDetailFragment.class,"headName",newsInfo.getHeadName());
        });
    }

}
