package com.zzti.youbang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zzti.youbang.R;
import com.zzti.youbang.bean.CommentBean;
import com.zzti.youbang.bean.CommentManageBean;
import com.zzti.youbang.net.BaseResponse;
import com.zzti.youbang.net.RetrofitManager;
import com.zzti.youbang.net.api.JavaApi;
import com.zzti.youbang.utils.PreferencesUtils;
import com.zzti.youbang.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CommentQuestionActivity extends AppCompatActivity {


    List<CommentBean> commentList = new ArrayList<>();

    Adapter adapter;
    @BindView(R.id.llNoResult)
    LinearLayout llNoResult;
    @BindView(R.id.lvComment)
    ListView lvComment;
    @BindView(R.id.btComment)
    Button btComment;
    @BindView(R.id.etComment)
    EditText etComment;
    private String question_id = "";
    private String user_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_question);
        ButterKnife.bind(this);

        user_id = PreferencesUtils.getString(CommentQuestionActivity.this,"user_id");

        Intent intent = getIntent();
        question_id = intent.getStringExtra("question_id");

        adapter = new Adapter();
        lvComment.setAdapter(adapter);

        loadMore();

    }

    public void loadMore() {
        RetrofitManager.getInstance().createReq(JavaApi.class).getComments(question_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<CommentManageBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(BaseResponse<CommentManageBean> commentManageBean) {
                        commentList = commentManageBean.data.getList();
                        if (commentList.size() == 0) {
                            llNoResult.setVisibility(View.VISIBLE);
                            lvComment.setVisibility(View.GONE);
                        }

                        adapter.notifyDataSetChanged();
                    }
                });

    }

    @OnClick(R.id.btComment)
    public void onClick() {

        if(StringUtils.isNotEmpty(etComment.getText().toString())){

            RetrofitManager.getInstance().createReq(JavaApi.class).comment(user_id,etComment.getText().toString(),question_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                            StringUtils.showToast(CommentQuestionActivity.this,e.getMessage());


                        }

                        @Override
                        public void onNext(BaseResponse baseResponse) {

                            StringUtils.showToast(CommentQuestionActivity.this,baseResponse.msg);


                        }
                    });


        }else{
            StringUtils.showToast(CommentQuestionActivity.this,"评论内容不能为空！");
        }


    }


    public class Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return commentList.size();
        }

        @Override
        public Object getItem(int position) {
            return commentList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.activity_comment_question_item, null);
                viewHolder = new ViewHolder(convertView);

                viewHolder.cvImage = (CircleImageView) convertView.findViewById(R.id.cvImage);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
                viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
                viewHolder.tvPrise = (TextView) convertView.findViewById(R.id.tvPrise);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            StringUtils.filtNull(viewHolder.tvName, commentList.get(position).getName());
            StringUtils.filtNull(viewHolder.tvContent, commentList.get(position).getContent());
            StringUtils.filtNull(viewHolder.tvTime, commentList.get(position).getCreated_time());

            ImageLoader.getInstance().displayImage(commentList.get(position).getPic(), viewHolder.cvImage);

            viewHolder.cvImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CommentQuestionActivity.this, HomePageActivity.class);
                    intent.putExtra("user_id", commentList.get(position).getUser_id());
                    startActivity(intent);
                }
            });


            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.cvImage)
            CircleImageView cvImage;
            @BindView(R.id.tvName)
            TextView tvName;
            @BindView(R.id.tvContent)
            TextView tvContent;
            @BindView(R.id.tvTime)
            TextView tvTime;
            @BindView(R.id.tvPrise)
            TextView tvPrise;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


}
