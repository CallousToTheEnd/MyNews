package com.lk.mynews.Activity;

import android.support.v7.app.AppCompatActivity;

import com.lk.mynews.R;

/**
 * Created by Mr.li on 2016/2/6.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.empty, R.anim.slide_toright);
    }

}
