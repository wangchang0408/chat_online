package com.maple.chat.fragment.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maple.chat.R;
import com.maple.chat.adapter.news.NewaDetailAdapter;
import com.maple.chat.core.BaseFragment;
import com.maple.chat.utils.DemoDataProvider;
import com.maple.chat.utils.Status;
import com.maple.chat.utils.XToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import butterknife.BindView;


/**
 * @author: wangchang
 * @date: 2020/9/9
 */
@Page(anim = CoreAnim.none)
public class NewsDetailFragment extends BaseFragment {


    private ImageView titleBack;

    private TextView titleName;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_stateful)
    StatefulLayout mLlStateful;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private NewaDetailAdapter newaDetailAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void initViews() {
        titleBack = findViewById(R.id.title_back);
        titleName = findViewById(R.id.title_name);
        //根据上个页面传的值进行赋值
        if (getArguments() != null) {
            String name = getArguments().getString("headName");
            titleName.setText(name);
        }
        initListeners();

        WidgetUtils.initRecyclerView(mRecyclerView);

        mRecyclerView.setAdapter(newaDetailAdapter = new NewaDetailAdapter());

        refresh();
    }

    @Override
    protected void initListeners() {
        titleBack.setOnClickListener(view -> {
            popToBack();
        });
    }


    private void refresh() {
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Status status = getRefreshStatus();
                        switch(status) {
                            case SUCCESS:
                                newaDetailAdapter.refresh(DemoDataProvider.getDemoData());
                                mRefreshLayout.resetNoMoreData();//setNoMoreData(false);
                                mLlStateful.showContent();
                                mRefreshLayout.setEnableLoadMore(true);
                                break;
                            case EMPTY:
                                mLlStateful.showEmpty();
                                mRefreshLayout.setEnableLoadMore(false);
                                break;
                            case ERROR:
                                showError();
                                break;
                            case NO_NET:
                                showOffline();
                                break;
                            default:
                                break;
                        }
                        refreshLayout.finishRefresh();

                    }
                }, 2000);
            }
        });
        //上拉加载
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (newaDetailAdapter.getItemCount() > 30) {
                            XToastUtils.toast("数据全部加载完毕");
                            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                        } else {
                            newaDetailAdapter.loadMore(DemoDataProvider.getDemoData());
                            refreshLayout.finishLoadMore();
                        }
                    }
                }, 2000);
            }
        });
        mRefreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }

    private void showOffline() {
        mLlStateful.showOffline(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.autoRefresh();
            }
        });
        mRefreshLayout.setEnableLoadMore(false);
    }

    private void showError() {
        mLlStateful.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.autoRefresh();
            }
        });
        mRefreshLayout.setEnableLoadMore(false);
    }


    private Status getRefreshStatus() {
        int status = (int) (Math.random() *  10);
        if (status % 2 == 0) {
            return Status.SUCCESS;
        } else if (status % 3 == 0) {
            return Status.EMPTY;
        } else if (status % 5 == 0) {
            return Status.ERROR;
        } else {
            return Status.NO_NET;
        }
    }

}
