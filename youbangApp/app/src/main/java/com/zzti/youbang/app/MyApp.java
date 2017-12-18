package com.zzti.youbang.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zzti.youbang.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengyongge
 * @des
 * @date 2017/5/4 0004
 */

public class MyApp extends Application {

    private static MyApp instance;
    private static List<Activity> con_list = new ArrayList<Activity>();
    private SharedPreferences sp;


    @Override
    public void onCreate() {
        super.onCreate();

        //发布关闭
         LogUtil.init(AppConfig.DEBUG);
         initImageLoad();
    }


    /**
     * 初始化imageload
     */
    public void initImageLoad(){

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_stub) //取消配置防止oom
//                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisc(true)
//                .cacheOnDisk(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).build();

        ImageLoader.getInstance().init(config);
    }


    public static void addActivity(Activity activity) {
        con_list.add(activity);
    }

    public static void killActivity() {
        for (int i = 0; i < con_list.size(); i++) {
            con_list.get(i).finish();
        }
    }

    public static MyApp getInstance() {
        return instance;
    }
    public SharedPreferences getMustElement() {
        sp = getSharedPreferences("MUST", MODE_PRIVATE);
        return sp;
    }


    /**
     * 获取版本号
     */
    public String getVersionName() {
        String versionName; // 版本号
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            versionName = pi.versionName; // 获取在AndroidManifest.xml中配置的版本号
        } catch (PackageManager.NameNotFoundException e) {
            versionName = "";
        }
        return versionName;
    }



}
