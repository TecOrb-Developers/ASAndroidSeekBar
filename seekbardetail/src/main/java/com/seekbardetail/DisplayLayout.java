package com.seekbardetail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.seekbardetail.Utils.Size;

public class DisplayLayout extends LinearLayout {

    public DisplayLayout(Context context) {
        this(context, null);
    }

    public DisplayLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DisplayLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            layoutIndicator(getChildAt(i), i);
        }
        super.onFinishInflate();
    }


    public void attachTo(DisplayingSeekBar seekBar) {
        attachTo(seekBar, -2);
    }

    public void attachTo(DisplayingSeekBar seekBar, int index) {
        if (seekBar == null) {
            throw new NullPointerException("the seek bar wanna attach to IndicatorStayLayout " +
                    "can not be null value.");
        }
        layoutIndicator(seekBar, index);
        addView(seekBar, index + 1);
    }


    private void layoutIndicator(View child, int index) {
        if (child instanceof DisplayingSeekBar) {
            DisplayingSeekBar seekBar = (DisplayingSeekBar) child;
            seekBar.setIndicatorStayAlways(true);
            View contentView = seekBar.getIndicatorContentView();
            if (contentView == null) {
                throw new IllegalStateException("Can not find any indicator in the IndicatorSeekBar, please " +
                        "make sure you have called the attr: SHOW_INDICATOR_TYPE for IndicatorSeekBar and the value is not IndicatorType.NONE.");
            }
            if (contentView instanceof DisplayingSeekBar) {
                throw new IllegalStateException("IndicatorSeekBar can not be a contentView for Indicator in case this inflating loop.");
            }
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            MarginLayoutParams layoutParams = new MarginLayoutParams(params);
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,
                    layoutParams.rightMargin, Size.dp2px(seekBar.getContext(), 2) - seekBar.getPaddingTop());
            addView(contentView, index, layoutParams);
            seekBar.showStayIndicator();
        }
    }

    @Override
    public void setOrientation(int orientation) {
        if (orientation != VERTICAL) {
            throw new IllegalArgumentException("IndicatorStayLayout is always vertical and does"
                    + " not support horizontal orientation");
        }
        super.setOrientation(orientation);
    }

}
