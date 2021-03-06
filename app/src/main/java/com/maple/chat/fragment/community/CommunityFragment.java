package com.maple.chat.fragment.community;

import com.maple.chat.R;
import com.maple.chat.core.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

/**
 * @author wangchang
 * @date 2020/9/7
 */
@Page(anim = CoreAnim.none)
public class CommunityFragment extends BaseFragment {


    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

    }
}





