package com.zzti.youbang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzti.youbang.R;
import com.zzti.youbang.bean.LoginBean;
import com.zzti.youbang.net.BaseResponse;
import com.zzti.youbang.net.RetrofitManager;
import com.zzti.youbang.net.api.JavaApi;
import com.zzti.youbang.utils.PreferencesUtils;
import com.zzti.youbang.utils.StringUtils;
import com.zzti.youbang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.cvImage)
    CircleImageView cvImage;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDes)
    TextView tvDes;

    @BindView(R.id.tvQuestions)
    TextView tvQuestions;
    @BindView(R.id.tvComment)
    TextView tvComment;
    @BindView(R.id.rlMyComment)
    RelativeLayout rlMyComment;
    @BindView(R.id.rlMyQuestion)
    RelativeLayout rlMyQuestion;



    private String user_id = "";
    Intent intent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");

        if (StringUtils.isEmpty(user_id)) {
            user_id = PreferencesUtils.getString(HomePageActivity.this, "user_id");
        }


        RetrofitManager.getInstance().createReq(JavaApi.class).getInfor(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<LoginBean>>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {


                        ToastUtils.showToast(HomePageActivity.this, e.getMessage());

                    }

                    @Override
                    public void onNext(BaseResponse<LoginBean> loginBean) {

                        StringUtils.filtNull(tvName, loginBean.data.getName());
                        StringUtils.filtNull(tvDes, loginBean.data.getDes());
                        StringUtils.filtNull(tvQuestions, "1");
                        StringUtils.filtNull(tvComment, "1");
                    }

                });

    }




    @OnClick({R.id.rlItem,R.id.rlMyQuestion, R.id.rlMyComment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlMyComment:
                intent = new Intent(HomePageActivity.this, MyCommentActivity.class);
                startActivity(intent);
                break;
            case R.id.rlMyQuestion:
                intent = new Intent(HomePageActivity.this, MyPublishQuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.rlItem:
                intent = new Intent(HomePageActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
