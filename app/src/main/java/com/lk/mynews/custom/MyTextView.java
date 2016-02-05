package com.lk.mynews.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Mr.li on 2016/2/5.
 */
public class MyTextView extends TextView {

    private final String namespace = "http://com.lk.mynewws";

    private int resourceId = 0;
    private Bitmap bitmap;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        resourceId = attrs.getAttributeResourceValue(namespace, "src", 0);
        if (resourceId > 0) {
            bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        }
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null) {
            Rect src = new Rect();
            Rect target = new Rect();
            src.left = 0;
            src.top = 0;
            src.right = bitmap.getWidth();
            src.bottom = bitmap.getHeight();
            int textHeight = (int) getTextSize();
            target.left = 0;
            target.top = (int)((getMeasuredHeight() - getTextSize()) /2) + 1;
            target.bottom = target.top + textHeight;
            target.right = (int)(textHeight * (bitmap.getWidth() / (float)bitmap.getHeight()));
            canvas.drawBitmap(bitmap, src, target, getPaint());
            canvas.translate(target.right + 2, 0);
        }
        super.onDraw(canvas);
    }
}
