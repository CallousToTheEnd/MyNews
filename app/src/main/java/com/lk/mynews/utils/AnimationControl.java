package com.lk.mynews.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by Mr.li on 2016/2/2.
 */
public class AnimationControl {

    private ImageView mLoadingImage;

    private ObjectAnimator objectAnimator;

    public void initLoadingAnimation(ImageView loadingImage) {
        this.mLoadingImage = loadingImage;
        objectAnimator = ObjectAnimator.ofFloat(mLoadingImage, "Rotation", 0, 360);
        objectAnimator.setDuration(1000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    public void showLoadingAnimation() {
       objectAnimator.start();
    }

    /**
     * 取消加载动画
     */
    public void cancelLoadingAnimation() {
        if (objectAnimator.isRunning() | objectAnimator.isStarted()) {
            objectAnimator.cancel();
        }
        mLoadingImage.setVisibility(View.GONE);
    }

    public ObjectAnimator getLoadingAnimator() {
        return objectAnimator;
    }

}
