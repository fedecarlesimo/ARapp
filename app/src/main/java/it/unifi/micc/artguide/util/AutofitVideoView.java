package it.unifi.micc.artguide.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.VideoView;

public class AutofitVideoView extends VideoView {

    private int mVideoWidth;
    private int mVideoHeight;

    public AutofitVideoView(Context context) {
        super(context);
    }

    public AutofitVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutofitVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setVideoSize(int width, int height) {
        mVideoWidth = width;
        mVideoHeight = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        View parent = (View)getParent();
//        int parentWidth = parent.getWidth();
//        int parentHeight = parent.getHeight();
//
//        if (parentWidth == 0 || parentHeight == 0) {
//            setMeasuredDimension(1, 1);
//            return;
//        }

        if (mVideoHeight == 0 || mVideoWidth == 0) {
            setMeasuredDimension(100, 100);
            return;
        }

        float dip = 10.f;
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int parentHeight = displayMetrics.heightPixels - 2 * px;
        int parentWidth = displayMetrics.widthPixels - 2 * px;

        int requestedWidth = parentWidth;
        int requestedHeight = (int)(((float)requestedWidth) * ((float)mVideoHeight) / ((float)mVideoWidth));

        int width = getDefaultSize(requestedWidth, widthMeasureSpec);
        int height = getDefaultSize(requestedHeight, heightMeasureSpec);
        if (requestedWidth > 0 && requestedHeight > 0) {
            if (requestedWidth * height > width * requestedHeight) {
                // Log.i("@@@", "image too tall, correcting");
                height = width * requestedHeight / requestedWidth;
            } else if (requestedWidth * height < width * requestedHeight) {
                // Log.i("@@@", "image too wide, correcting");
                width = height * requestedWidth / requestedHeight;
            } else {
                // Log.i("@@@", "aspect ratio is correct: " +
                // width+"/"+height+"="+
                // mVideoWidth+"/"+mVideoHeight);
            }
        }

        Log.i("@@@", "parent size: " + parentWidth + 'x' + parentHeight);
        Log.i("@@@", "setting size: " + requestedWidth + 'x' + requestedHeight);

        setMeasuredDimension(requestedWidth, requestedHeight);
    }
}
