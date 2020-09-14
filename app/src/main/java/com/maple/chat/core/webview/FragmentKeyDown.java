package com.maple.chat.core.webview;

import android.view.KeyEvent;

/**
 *
 *
 * @author wangchang
 * @since 2019/1/4 下午11:32
 */
public interface FragmentKeyDown {

    /**
     * fragment按键监听
     * @param keyCode
     * @param event
     * @return
     */
    boolean onFragmentKeyDown(int keyCode, KeyEvent event);
}
