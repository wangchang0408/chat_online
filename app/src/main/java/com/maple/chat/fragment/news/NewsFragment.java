package com.maple.chat.fragment.news;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.maple.chat.R;
import com.maple.chat.adapter.news.NewsListItemAdapter;
import com.maple.chat.core.BaseFragment;
import com.maple.chat.entity.NewsInfo;
import com.maple.chat.utils.TimeUtils;
import com.maple.chat.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 消息动态
 *
 * @author wangchang
 * @since 2019-10-30 00:15
 */
@Page(anim = CoreAnim.none)
public class NewsFragment extends BaseFragment {
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.empty_view)
    LinearLayout emptyView;

    private NewsListItemAdapter newsListItemAdapter;

    @BindView(R.id.recycler_view)
    SwipeRecyclerView recyclerView;

    NewsFragment newsFragment = this;

    List<NewsInfo> newsInfos = new ArrayList<>();

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        WidgetUtils.initRecyclerView(recyclerView);

        initData();
        //必须在setAdapter之前调用
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        //必须在setAdapter之前调用
        recyclerView.setOnItemMenuClickListener(mMenuItemClickListener);
        if (newsInfos.isEmpty()) {
            refreshLayout.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            newsListItemAdapter = new NewsListItemAdapter(newsInfos, newsFragment);
            recyclerView.setAdapter(newsListItemAdapter);
        }


        refreshLayout.setColorSchemeColors(0xff0099cc, 0xffff4444, 0xff669900, 0xffaa66cc, 0xffff8800);
    }

    //测试数据
    private void initData() {
        NewsInfo newsInfo1 = new NewsInfo();
        newsInfo1.setHeadImage("1");
        newsInfo1.setHeadName("王同学");
        newsInfo1.setHeadMsg("李同学，十一一起回家啊！");
        newsInfo1.setHeadDate(TimeUtils.parse4Str(new Date()));

        NewsInfo newsInfo2 = new NewsInfo();
        newsInfo2.setHeadImage("1");
        newsInfo2.setHeadName("李同学");
        newsInfo2.setHeadMsg("好啊，王同学！");
        newsInfo2.setHeadDate(TimeUtils.parse4Str(new Date()));

        newsInfos.add(newsInfo1);
        newsInfos.add(newsInfo2);

    }


    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }


    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext()).setBackground(R.drawable.menu_selector_red)
                        .setImage(R.drawable.ic_swipe_menu_delete)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

            }
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。

            if (direction == SwipeRecyclerView.RIGHT_DIRECTION) {
                XToastUtils.toast("list第" + position);
                NewsInfo temp = newsInfos.get(position);//由position找到list集合中的对象
                newsInfos.remove(temp);//从list中移出需要删除的那个对象
                newsListItemAdapter.refresh(newsInfos);
                if (newsInfos.isEmpty()) {
                    refreshLayout.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    @Override
    protected void initListeners() {
        //下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        refresh(); //第一次进入触发自动刷新，演示效果
    }

    private void refresh() {
        refreshLayout.setRefreshing(true);
        loadData();
    }

    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!newsInfos.isEmpty()){
                    newsListItemAdapter.refresh(newsInfos);
                }
                if (refreshLayout != null) {
                    refreshLayout.setRefreshing(false);
                }
            }
        }, 1000);
    }

}
