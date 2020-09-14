package com.maple.chat.adapter.news;

import com.maple.chat.R;
import com.scwang.smartrefresh.layout.adapter.SmartRecyclerAdapter;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.xuexiang.xui.utils.ResUtils;

import java.util.Collection;

/**
 * @author: wangchang
 * @date: 2020/9/9
 */
public class NewaDetailAdapter extends SmartRecyclerAdapter<String>{

    public NewaDetailAdapter() {
        super(android.R.layout.simple_list_item_2);
    }

    public NewaDetailAdapter(Collection<String> data) {
        super(data, android.R.layout.simple_list_item_2);
    }

    /**
     * 绑定布局控件
     *
     * @param holder
     * @param model
     * @param position
     */
    @Override
    protected void onBindViewHolder(SmartViewHolder holder, String model, int position) {
        holder.text(android.R.id.text1, ResUtils.getResources().getString(R.string.item_example_number_title, position));
        holder.text(android.R.id.text2, ResUtils.getResources().getString(R.string.item_example_number_abstract, position));
        holder.textColorId(android.R.id.text2, R.color.xui_config_color_light_blue_gray);
    }
}
