package com.zzti.youbang.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzti.youbang.R;
import com.zzti.youbang.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.tvVersion)
    TextView tvVersion;
    @BindView(R.id.rlClear)
    RelativeLayout rlClear;
    @BindView(R.id.rlAbout)
    RelativeLayout rlAbout;
    @BindView(R.id.tvLoginOut)
    TextView tvLoginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rlClear, R.id.rlAbout, R.id.tvLoginOut})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlClear:
                break;
            case R.id.rlAbout:
                break;
            case R.id.tvLoginOut:

                PreferencesUtils.putBoolean(SettingActivity.this,"login",false);

                break;
        }
    }
}
