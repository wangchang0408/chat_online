package com.maple.chat.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maple.chat.R;
import com.maple.chat.activity.MainActivity;
import com.maple.chat.core.BaseFragment;
import com.maple.chat.utils.JellyInterpolator;
import com.maple.chat.utils.RandomUtils;
import com.maple.chat.utils.RegularUtil;
import com.maple.chat.utils.TextUtils;
import com.maple.chat.utils.TokenUtils;
import com.maple.chat.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.core.CoreSwitchBean;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.PasswordEditText;
import com.xuexiang.xutil.app.ActivityUtils;


/**
 * 登录页面
 *
 * @author wangchang
 * @since 2020/9/5
 */
@Page(anim = CoreAnim.none)
public class LoginFragment extends BaseFragment implements View.OnClickListener{
    private TextView mBtnLogin;

    private TextView registerUser;

    private View progress;

    private View mInputLayout;

    //登录账号和密码
    private EditText loginUserName;
    private PasswordEditText loginUserPwd;

    private float mWidth, mHeight;

    private LinearLayout mName, mPsw;

    //新用户注册弹出窗
    MaterialDialog.Builder registerDialog;
    MaterialDialog registerDialogTemp;

    //新用户注册所需内容
    private EditText newUserName;
    private PasswordEditText newUserPwd;
    private PasswordEditText newUserRepwd;
    private EditText newUserTelNum;
    private EditText newUserEmail;
    private Button btnCancle;
    private Button btnSure;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    //初始化页面，去掉页面头部
    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle()
                .setImmersive(true);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle("");
        titleBar.disableLeftView();
        return titleBar;
    }



    @Override
    protected void initViews() {
        initView();
        onClickRegister();
    }

    //注册用户
    private void onClickRegister() {
        registerUser.setOnClickListener(v -> {
            registerDialog();
            registerDialogTemp = registerDialog.show();
        });

    }

    //注册用户弹框
    private void registerDialog() {
        if (null == registerDialogTemp || !registerDialogTemp.isShowing()) {
            registerDialog = new MaterialDialog.Builder(getContext()).customView(R.layout.register_dialog, false).cancelable(false);
        }
        View view = registerDialog.build().getView();
        newUserName = view.findViewById(R.id.new_user_name);
        newUserPwd = view.findViewById(R.id.new_user_pwd);
        newUserRepwd = view.findViewById(R.id.new_user_rePwd);
        newUserTelNum = view.findViewById(R.id.new_user_telNum);
        newUserEmail = view.findViewById(R.id.new_user_email);
        btnCancle = view.findViewById(R.id.btn_cancel);
        btnSure = view.findViewById(R.id.btn_sure);

        //取消按钮
        btnCancle.setOnClickListener(v -> {
            registerDialogTemp.dismiss();
        });

        btnSure.setOnClickListener(v -> {
            String msg=checkData();
            if(msg.equals("success")){
                // TODO: 提交用户注册数据到后台

            }else {
                XToastUtils.error(msg);
            }
        });

    }

    //校验提交的数据是否正确
    private String checkData() {
        String msg = "";
        if (TextUtils.isEmpty(newUserName.getText().toString()) || TextUtils.isEmpty(newUserPwd.getText().toString()) ||
                TextUtils.isEmpty(newUserRepwd.getText().toString()) || TextUtils.isEmpty(newUserTelNum.getText().toString()) ||
                TextUtils.isEmpty(newUserEmail.getText().toString())) {
            msg = "账号、密码、手机号、邮箱不允许为空！！";
            return msg;
        } else if (!TextUtils.equals(newUserRepwd.getText().toString(),newUserPwd.getText().toString())) {
            msg = "两次密码输入不一致！！";
            return msg;
        } else if (!RegularUtil.isMobile(newUserTelNum.getText().toString())) {
            msg = "手机格式不正确！！";
            return msg;
        } else if (!RegularUtil.isEmail(newUserEmail.getText().toString())) {
            msg = "邮箱格式不正确！！";
            return msg;
        } else {
            msg = "success";
            return msg;
        }
    }



    //初始化
    private void initView() {
        mBtnLogin = findViewById(R.id.main_btn_login);
        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
        mName = findViewById(R.id.input_layout_name);
        mPsw = findViewById(R.id.input_layout_psw);
        registerUser = findViewById(R.id.register_user);
        loginUserName=findViewById(R.id.login_user_name);
        loginUserPwd=findViewById(R.id.login_user_pwd);
        mBtnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        // 计算出控件的高与宽
        mWidth = mBtnLogin.getMeasuredWidth();
        mHeight = mBtnLogin.getMeasuredHeight();
        // 隐藏输入框
        mName.setVisibility(View.INVISIBLE);
        mPsw.setVisibility(View.INVISIBLE);

        inputAnimator(mInputLayout, mWidth, mHeight);


    }


    /**
     * 输入框的动画效果
     *
     * @param view 控件
     * @param w    宽
     * @param h    高
     */
    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);
                String token = RandomUtils.getRandomNumbersAndLetters(16);
                //登录到主页
                String UserName=loginUserName.getText().toString();
                String UserPwd=loginUserPwd.getText().toString();
                //TODO 登录接口

                if (TokenUtils.handleLoginSuccess(token)) {
                    popToBack();
                    ActivityUtils.startActivity(MainActivity.class);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }

    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }


    /**
     * 打开fragment
     *
     * @return 打开的fragment对象
     */
    public <T extends XPageFragment> T openNewPage(Class<T> clazz) {
        CoreSwitchBean page = new CoreSwitchBean(clazz)
                .setNewActivity(true);
        XPageActivity xPageActivity=new XPageActivity();
        return (T) xPageActivity.openPage(page);
    }


}

