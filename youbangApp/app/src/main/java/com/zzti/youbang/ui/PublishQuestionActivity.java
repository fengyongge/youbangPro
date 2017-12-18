package com.zzti.youbang.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzti.youbang.R;
import com.zzti.youbang.alert.ProgressBarHelper;
import com.zzti.youbang.bean.LoginBean;
import com.zzti.youbang.net.BaseResponse;
import com.zzti.youbang.net.RetrofitManager;
import com.zzti.youbang.net.api.JavaApi;
import com.zzti.youbang.utils.PreferencesUtils;
import com.zzti.youbang.utils.ToastUtils;
import com.zzti.youbang.view.XWEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PublishQuestionActivity extends AppCompatActivity {

    @BindView(R.id.etTitle)
    EditText etTitle;
    @BindView(R.id.xeContent)
    XWEditText xeContent;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.rlRight)
    RelativeLayout rlRight;
    private String userId;

    Dialog windowsBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_question);
        ButterKnife.bind(this);
        userId = PreferencesUtils.getString(PublishQuestionActivity.this, "userId");

        tvRight.setText("发布");



    }


    @OnClick(R.id.rlRight)
    public void onViewClicked() {

        windowsBar = ProgressBarHelper.createWindowsBar(this);


        RetrofitManager.getInstance().createReq(JavaApi.class).publishedQuestion(
                userId, etTitle.getText().toString(), xeContent.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<LoginBean>>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        if (windowsBar.isShowing() && windowsBar != null) {
                            windowsBar.dismiss();
                        }


                        ToastUtils.showToast(PublishQuestionActivity.this, e.getMessage());

                    }

                    @Override
                    public void onNext(BaseResponse<LoginBean> loginBeanBaseResponse) {
                        if (windowsBar.isShowing() && windowsBar != null) {
                            windowsBar.dismiss();
                        }


                    }

                });


    }
}
