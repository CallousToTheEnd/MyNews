package com.lk.mynews.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.mynews.R;

/**
 * Created by Mr.li on 2016/2/5.
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView iv_setting_toolbar_back;
    private TextView tv_other_account, tv_personal_setting, tv_column_plugin,
                tv_feed_back, tv_recommand, tv_rating_guite, tv_show_ad, tv_about;

    private RelativeLayout rl_setting_font_panel,rl_setting_text_size,rl_setting_push_zone,
            rl_setting_personal_zone, rl_setting_night_zone, rl_setting_offline_zone,
            rl_setting_no_picture_zone, rl_setting_clear_cache,
            rl_setting_check_updata_zone;

    private CheckBox cb_setting_push, cb_setting_personal_switch,
            cb_setting_night_switch, cb_setting_offline, cb_setting_picture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        iv_setting_toolbar_back = (ImageView) findViewById(R.id.iv_setting_toolbar_back);
        tv_other_account = (TextView) findViewById(R.id.tv_setting_other_account);
        tv_personal_setting = (TextView) findViewById(R.id.tv_setting_personal_setting);
        tv_column_plugin = (TextView) findViewById(R.id.tv_setting_column_plugin);
        tv_feed_back = (TextView) findViewById(R.id.tv_setting_feed_back);
        tv_recommand = (TextView) findViewById(R.id.tv_setting_recommand);
        tv_rating_guite = (TextView) findViewById(R.id.tv_setting_rating_guide);
        tv_show_ad = (TextView) findViewById(R.id.tv_setting_show_ad);
        tv_about = (TextView) findViewById(R.id.tv_setting_about);
        rl_setting_font_panel = (RelativeLayout) findViewById(R.id.rl_setting_font_panel);
        rl_setting_text_size = (RelativeLayout) findViewById(R.id.rl_setting_text_size);
        rl_setting_push_zone = (RelativeLayout) findViewById(R.id.rl_setting_push_zone);
        rl_setting_personal_zone = (RelativeLayout) findViewById(R.id.rl_setting_personal_zone);
        rl_setting_night_zone = (RelativeLayout) findViewById(R.id.rl_setting_night_zone);
        rl_setting_offline_zone = (RelativeLayout) findViewById(R.id.rl_setting_offline_zone);
        rl_setting_no_picture_zone = (RelativeLayout) findViewById(R.id.rl_setting_no_picture_zone);
        rl_setting_clear_cache = (RelativeLayout) findViewById(R.id.rl_setting_clear_cache);
        rl_setting_check_updata_zone = (RelativeLayout) findViewById(R.id.rl_setting_check_updata_zone);
        cb_setting_push = (CheckBox) findViewById(R.id.cb_setting_push);
        cb_setting_personal_switch = (CheckBox) findViewById(R.id.cb_setting_personal_switch);
        cb_setting_night_switch = (CheckBox) findViewById(R.id.cb_setting_night_switch);
        cb_setting_offline = (CheckBox) findViewById(R.id.cb_setting_offline);
        cb_setting_picture = (CheckBox) findViewById(R.id.cb_setting_picture);

        iv_setting_toolbar_back.setOnClickListener(this);
        tv_other_account.setOnClickListener(this);
        tv_personal_setting.setOnClickListener(this);
        tv_column_plugin.setOnClickListener(this);
        tv_feed_back.setOnClickListener(this);
        tv_recommand.setOnClickListener(this);
        tv_rating_guite.setOnClickListener(this);
        tv_show_ad.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        rl_setting_font_panel.setOnClickListener(this);
        rl_setting_text_size.setOnClickListener(this);
        rl_setting_push_zone.setOnClickListener(this);
        rl_setting_personal_zone.setOnClickListener(this);
        rl_setting_night_zone.setOnClickListener(this);
        rl_setting_offline_zone.setOnClickListener(this);
        rl_setting_no_picture_zone.setOnClickListener(this);
        rl_setting_clear_cache.setOnClickListener(this);
        rl_setting_check_updata_zone.setOnClickListener(this);
        cb_setting_push.setOnCheckedChangeListener(this);
        cb_setting_personal_switch.setOnCheckedChangeListener(this);
        cb_setting_night_switch.setOnCheckedChangeListener(this);
        cb_setting_offline.setOnCheckedChangeListener(this);
        cb_setting_picture.setOnCheckedChangeListener(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.empty, R.anim.slide_toright);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_setting_toolbar_back:
                SettingActivity.this.finish();
                break;
            case R.id.tv_setting_other_account:
                Toast.makeText(SettingActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting_personal_setting:
                Toast.makeText(SettingActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting_column_plugin:
                Toast.makeText(SettingActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting_feed_back:
                Toast.makeText(SettingActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting_recommand:
                Toast.makeText(SettingActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting_rating_guide:
                Toast.makeText(SettingActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting_show_ad:
                Toast.makeText(SettingActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_setting_about:
                Toast.makeText(SettingActivity.this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_setting_font_panel:
                TextView t1 = (TextView)(v.findViewById(R.id.tv_setting_text_font));
                Toast.makeText(SettingActivity.this, t1.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_setting_text_size:
                TextView t2 = (TextView)(v.findViewById(R.id.tv_setting_application_text_size));
                Toast.makeText(SettingActivity.this, t2.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_setting_push_zone:
                CheckBox cb1= (CheckBox) v.findViewById(R.id.cb_setting_push);
                if (cb1.isChecked()) {
                    cb1.setChecked(false);
                } else {
                    cb1.setChecked(true);
                }
                break;
            case R.id.rl_setting_personal_zone:
                CheckBox cb2= (CheckBox) v.findViewById(R.id.cb_setting_personal_switch);
                if (cb2.isChecked()) {
                    cb2.setChecked(false);
                } else {
                    cb2.setChecked(true);
                }
                break;
            case R.id.rl_setting_night_zone:
                CheckBox cb3 = (CheckBox) v.findViewById(R.id.cb_setting_night_switch);
                if (cb3.isChecked()) {
                    cb3.setChecked(false);
                } else {
                    cb3.setChecked(true);
                }
                break;
            case R.id.rl_setting_offline_zone:
                CheckBox cb4 = (CheckBox) v.findViewById(R.id.cb_setting_offline);
                if (cb4.isChecked()) {
                    cb4.setChecked(false);
                } else {
                    cb4.setChecked(true);
                }
                break;
            case R.id.rl_setting_no_picture_zone:
                CheckBox cb5 = (CheckBox) v.findViewById(R.id.cb_setting_picture);
                if (cb5.isChecked()) {
                    cb5.setChecked(false);
                } else {
                    cb5.setChecked(true);
                }
                break;
            case R.id.rl_setting_clear_cache:
                TextView t8 = (TextView)(v.findViewById(R.id.tv_setting_clear_cache_manually));
                Toast.makeText(SettingActivity.this, t8.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_setting_check_updata_zone:
                TextView t9= (TextView)(v.findViewById(R.id.tv_setting_check_updata));
                Toast.makeText(SettingActivity.this, t9.getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_push:
                if (isChecked) {
                    Toast.makeText(SettingActivity.this, "打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cb_setting_personal_switch:
                if (isChecked) {
                    Toast.makeText(SettingActivity.this, "打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cb_setting_night_switch:
                if (isChecked) {
                    Toast.makeText(SettingActivity.this, "打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cb_setting_offline:
                if (isChecked) {
                    Toast.makeText(SettingActivity.this, "打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cb_setting_picture:
                if (isChecked) {
                    Toast.makeText(SettingActivity.this, "打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
