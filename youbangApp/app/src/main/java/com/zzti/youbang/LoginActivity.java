package com.zzti.youbang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zzti.youbang.bean.LoginBean;
import com.zzti.youbang.net.BaseResponse;
import com.zzti.youbang.net.RetrofitManager;
import com.zzti.youbang.net.api.JavaApi;
import com.zzti.youbang.utils.LogUtil;
import com.zzti.youbang.utils.PreferencesUtils;
import com.zzti.youbang.utils.ToastUtils;
import com.zzti.youbang.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.ceUserName)
    ClearEditText ceUserName;
    @BindView(R.id.cePassWord)
    ClearEditText cePassWord;
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if(PreferencesUtils.getBoolean(LoginActivity.this,"login")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @OnClick(R.id.tvLogin)
    public void onViewClicked() {

        RetrofitManager.getInstance().createReq(JavaApi.class).login(ceUserName.getText().toString(), cePassWord.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<LoginBean>>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        ToastUtils.showToast(LoginActivity.this,e.getMessage());

                    }

                    @Override
                    public void onNext(BaseResponse<LoginBean> loginBean) {

                        PreferencesUtils.putBoolean(LoginActivity.this,"login",true);

                        PreferencesUtils.putString(LoginActivity.this,"userId",loginBean.data.getId());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    }

                });

    }
}
