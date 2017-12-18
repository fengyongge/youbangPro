package com.zzti.youbang.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zzti.fengyongge.imagepicker.PhotoPreviewActivity;
import com.zzti.fengyongge.imagepicker.util.CommonUtils;
import com.zzti.youbang.R;
import com.zzti.youbang.bean.PhotoModel;
import com.zzti.youbang.bean.QuestionBean;
import com.zzti.youbang.bean.WelfareBean;
import com.zzti.youbang.net.BaseResponse;
import com.zzti.youbang.net.RetrofitManager2;
import com.zzti.youbang.net.api.JavaApi;
import com.zzti.youbang.view.RefreshLayout;
import com.zzti.youbang.view.SpacesItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelfareActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RefreshLayout.OnLoadListener {


    Adapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_ly)
    RefreshLayout swipeLy;
    List<WelfareBean> WelfareBeanList = new ArrayList<>();
    private boolean isClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);
        ButterKnife.bind(this);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        loadMore();
        swipeLy.setOnRefreshListener(this);
        swipeLy.setOnLoadListener(this);

    }


    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_welfare_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final WelfareBean welfareBean = WelfareBeanList.get(position);
            if (welfareBean.getUrl() != null) {
                ImageLoader.getInstance().displayImage(welfareBean.getUrl(), holder.iv_pic);
            } else {
                holder.iv_pic.setImageResource(R.drawable.ic_launcher);
            }

            final List<PhotoModel> single_photos = new ArrayList<PhotoModel>();
            for (int i = 0; i < WelfareBeanList.size(); i++) {
                PhotoModel photoModel = new PhotoModel();
                photoModel.setOriginalPath(WelfareBeanList.get(position).getUrl());
                single_photos.add(photoModel);
            }


            holder.iv_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<PhotoModel> single_photos = new ArrayList<PhotoModel>();
                    //PhotoModel 开发者将自己本地bean的list封装成PhotoModel的list，PhotoModel属性源码可查看
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("photos",(Serializable)single_photos);
                    bundle.putInt("position", position);//position预览图片地址
                    bundle.putBoolean("isSave",true);//isSave表示是否可以保存预览图片，建议只有预览网络图片时设置true
                    CommonUtils.launchActivity(WelfareActivity.this, PhotoPreviewActivity.class, bundle);

                }
            });

        }

        @Override
        public int getItemCount() {
            return WelfareBeanList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.iv_pic)
            ImageView iv_pic;

            public ViewHolder(View itemView) {
                super(itemView);

                ButterKnife.bind(this, itemView);
            }
        }
    }

    private void loadMore() {

        RetrofitManager2.getInstance().createReq(JavaApi.class).getWelfare()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponse data) {

                        if (isClear) {
                            WelfareBeanList.clear();
                        }

                        WelfareBeanList.addAll(JSON.parseArray(data.data.toString(),WelfareBean.class));

                        adapter.notifyDataSetChanged();
                        isClear = true;

                    }

                });

    }

    @Override
    public void onRefresh() {
        loadMore();
        swipeLy.setRefreshing(false);
    }

    @Override
    public void onLoad() {

    }

}
