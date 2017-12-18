package com.zzti.youbang.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zzti.youbang.R;
import com.zzti.youbang.alert.ProgressBarHelper;
import com.zzti.youbang.bean.QuestionBean;
import com.zzti.youbang.bean.QuestionManageBean;
import com.zzti.youbang.net.BaseResponse;
import com.zzti.youbang.net.RetrofitManager;
import com.zzti.youbang.net.api.JavaApi;
import com.zzti.youbang.utils.StringUtils;
import com.zzti.youbang.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyCommentActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RefreshLayout.OnLoadListener {

    List<QuestionBean> list = new ArrayList();

    @BindView(R.id.llNoResult)
    LinearLayout llNoResult;
    @BindView(R.id.lvQuestion)
    ListView lvQuestion;
    @BindView(R.id.rlSwipe)
    RefreshLayout rlSwipe;

    //分页
    private int totalResult = 0;
    private int pageIndex = 1;
    private int pageNumber = 20;
    private boolean isLoading = false;
    private boolean noMoreData = false;
    private boolean isRefresh = true;
    private boolean isClear = false;
    private boolean isClearData = false;
    private ProgressBar progressBar;
    MyCommentActivity.Adapter adapter;
    private Dialog windowsBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        ButterKnife.bind(this);

        View footerView = getLayoutInflater().inflate(R.layout.listview_footer, null);
        progressBar = (ProgressBar) footerView.findViewById(R.id.load_progress_bar);
        lvQuestion.addFooterView(footerView);
        adapter = new Adapter();
        lvQuestion.setAdapter(adapter);
        rlSwipe.setOnRefreshListener(this);
        rlSwipe.setOnLoadListener(this);
        rlSwipe.setChildView(lvQuestion);
        rlSwipe.setColorSchemeResources(R.color.blue);

        windowsBar = ProgressBarHelper.createWindowsBar(this);
        refreshgoods();

    }


    private void refreshgoods() {
        isClear = true;
        totalResult = 0;
        pageIndex = 1;
        pageNumber = 20;
        isLoading = false;
        noMoreData = false;
        loadMore();
    }


    public void loadMore() {

        if (windowsBar != null && windowsBar.isShowing()) {
            windowsBar.dismiss();
        }

        RetrofitManager.getInstance().createReq(JavaApi.class).getQuestion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse<QuestionManageBean>>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isRefresh) {
                            rlSwipe.setRefreshing(false);
                        }

                        if (windowsBar != null && windowsBar.isShowing()) {
                            windowsBar.dismiss();
                        }

                        StringUtils.showToast(MyCommentActivity.this, e.getMessage());

                    }

                    @Override
                    public void onNext(BaseResponse<QuestionManageBean> questionManageBean) {


                        if (windowsBar != null && windowsBar.isShowing()) {
                            windowsBar.dismiss();
                        }

                        if (isRefresh) {
                            rlSwipe.setRefreshing(false);
                        }

                        if (isLoading) {
                            rlSwipe.setLoading(false);
                            progressBar.setVisibility(View.GONE);
                        } else {
                            list.clear();
                        }

                        totalResult = Integer.parseInt(questionManageBean.data.getTotal());
                        list.addAll(questionManageBean.data.getList());

                        if(list.size()==0){
                            llNoResult.setVisibility(View.VISIBLE);
                            lvQuestion.setVisibility(View.GONE);
                        }

                        adapter.notifyDataSetChanged();

                        if (list.size() == 0
                                || (pageIndex == 1 && totalResult <= pageNumber)
                                || list.size() == totalResult) {
                            noMoreData = true;
                        } else {
                            noMoreData = false;
                            pageIndex++;
                        }

                        if (list.size() == 0) {
                            lvQuestion.setVisibility(View.GONE);
                            llNoResult.setVisibility(View.VISIBLE);
                        } else {
                            lvQuestion.setVisibility(View.VISIBLE);
                            llNoResult.setVisibility(View.GONE);
                        }

                        adapter.notifyDataSetChanged();
                    }

                });

    }


    @Override
    public void onLoad() {

        if (noMoreData == true) {
            StringUtils.showToast(MyCommentActivity.this, "没有更多");
            rlSwipe.setLoading(false);
        } else {
            isRefresh = false;
            isLoading = true;
            progressBar.setVisibility(View.VISIBLE);
            loadMore();
        }
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        refreshgoods();
    }


    public class Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.activity_main_item, null);
                viewHolder = new ViewHolder(convertView);

                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
                viewHolder.llItem = (LinearLayout) convertView.findViewById(R.id.llItem);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            StringUtils.filtNull(viewHolder.tvTitle, list.get(position).getTitle());
            StringUtils.filtNull(viewHolder.tvContent, list.get(position).getContent());
            StringUtils.filtNull(viewHolder.tvTime, list.get(position).getCreated_time());
            StringUtils.filtNull(viewHolder.tvName, list.get(position).getUser_infor().getName());
            ImageLoader.getInstance().displayImage(list.get(position).getUser_infor().getPic(), viewHolder.cvImage);


            viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyCommentActivity.this, QuestionDetailActivity.class);
                    intent.putExtra("id", list.get(position).getQuestion_id());
                    startActivity(intent);

                }
            });

            viewHolder.cvImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MyCommentActivity.this, HomePageActivity.class);
                    intent.putExtra("user_id", list.get(position).getUser_id());
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

            @BindView(R.id.tvTitle)
            TextView tvTitle;
            @BindView(R.id.tvContent)
            TextView tvContent;

            @BindView(R.id.tvTime)
            TextView tvTime;
            @BindView(R.id.llItem)
            LinearLayout llItem;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


}
