package com.zzti.youbang.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzti.youbang.R;
import com.zzti.youbang.bean.QuestionBean;
import com.zzti.youbang.net.BaseResponse;
import com.zzti.youbang.net.RetrofitManager;
import com.zzti.youbang.net.api.JavaApi;
import com.zzti.youbang.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuestionDetailActivity extends AppCompatActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.llComment)
    LinearLayout llComment;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        getQuestionDetail();

        tvNum.setText("评论 5");

              /* 显示App icon左侧的back键 */
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    public void getQuestionDetail() {
        RetrofitManager.getInstance().createReq(JavaApi.class).getQuestionDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<QuestionBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponse<QuestionBean> questionBean) {

                        StringUtils.filtNull(tvTitle, questionBean.data.getTitle());
                        StringUtils.filtNull(tvContent, questionBean.data.getContent());

                    }
                });

    }


    @OnClick(R.id.llComment)
    public void onViewClicked() {

        Intent intent = new Intent(QuestionDetailActivity.this,CommentQuestionActivity.class);
        intent.putExtra("question_id",id);
        startActivity(intent);


    }
}
