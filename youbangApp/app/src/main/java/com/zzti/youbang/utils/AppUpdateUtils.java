package com.zzti.youbang.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.io.File;

/**
 * @author fengyongge
 * @Description
 */
public class AppUpdateUtils {

    File file;
    View update_item;
    TextView tvConfirm;
    TextView tvCancle;
    TextView tvMessage;
    AlertDialog downloadalert;
    Context context;
    android.support.v7.app.AlertDialog.Builder builder;
    private File fileName= Environment.getExternalStorageDirectory();//目标文件存储的文件夹路径
    private String destFileName = "test.apk";//目标文件存储的文件名

    /**
     * 安装软件
     *
     * @param file
     */
    private void installApk(Context context, File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(install);
    }



    /**
     * 创建更新提醒对话框
     * @param context
     * @param message
     * @param download_url
     * @param isForceUpdate
     */
    public void showMaterialUpdateDialog(final Context context, String message, final String download_url, boolean isForceUpdate) {

        builder = new android.support.v7.app.AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("APP更新")
                .setMessage(Html.fromHtml(message))
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, DownLoadService.class);
                        intent.putExtra("download_url",download_url);
                        context.startService(intent);
                    }
                });

        if(isForceUpdate){

        }else{
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        builder.show();

    }

}


