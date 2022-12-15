package com.seekbardetail;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.seekbardetail.Callback.SeekBarIndicatorType;
import com.seekbardetail.Utils.Size;

public class DisplayValue {
    private final int mWindowWidth;
    private int[] mLocation = new int[2];
    private SeekbarArrowView mArrowView;
    private TextView mProgressTextView;
    private PopupWindow mIndicatorPopW;
    private LinearLayout mTopContentView;
    private int mGap;
    private int mIndicatorColor;
    private Context mContext;
    private int mIndicatorType;
    private DisplayingSeekBar mSeekBar;
    private View mIndicatorView;
    private View mIndicatorCustomView;
    private View mIndicatorCustomTopContentView;
    private float mIndicatorTextSize;
    private int mIndicatorTextColor;

    public DisplayValue(Context context,
                        DisplayingSeekBar seekBar,
                        int indicatorColor,
                        int indicatorType,
                        int indicatorTextSize,
                        int indicatorTextColor,
                        View indicatorCustomView,
                        View indicatorCustomTopContentView) {
        this.mContext = context;
        this.mSeekBar = seekBar;
        this.mIndicatorColor = indicatorColor;
        this.mIndicatorType = indicatorType;
        this.mIndicatorCustomView = indicatorCustomView;
        this.mIndicatorCustomTopContentView = indicatorCustomTopContentView;
        this.mIndicatorTextSize = indicatorTextSize;
        this.mIndicatorTextColor = indicatorTextColor;

        mWindowWidth = getWindowWidth();
        mGap = Size.dp2px(mContext, 2);
        initIndicator();
    }

    private void initIndicator() {
        if (mIndicatorType == SeekBarIndicatorType.CUSTOM) {
            if (mIndicatorCustomView != null) {
                mIndicatorView = mIndicatorCustomView;
                int progressTextViewId = mContext.getResources().getIdentifier("isb_progress", "id", mContext.getApplicationContext().getPackageName());
                if (progressTextViewId > 0) {
                    View view = mIndicatorView.findViewById(progressTextViewId);
                    if (view != null) {
                        if (view instanceof TextView) {
                            //progressText
                            mProgressTextView = (TextView) view;
                            mProgressTextView.setText(mSeekBar.getIndicatorTextString());
                            mProgressTextView.setTextSize(Size.px2sp(mContext, mIndicatorTextSize));
                            mProgressTextView.setTextColor(mIndicatorTextColor);
                        } else {
                            throw new ClassCastException("the view identified by isb_progress in indicator custom layout can not be cast to TextView");
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("the attr：indicator_custom_layout must be set while you set the indicator type to CUSTOM.");
            }
        } else {
            if (mIndicatorType == SeekBarIndicatorType.CIRCULAR_BUBBLE) {
                mIndicatorView = new CircleBubbleView(mContext, mIndicatorTextSize, mIndicatorTextColor, mIndicatorColor, "1000");
                ((CircleBubbleView) mIndicatorView).setProgress(mSeekBar.getIndicatorTextString());
            } else {
                mIndicatorView = View.inflate(mContext, R.layout.display_view, null);
                //container
                mTopContentView = (LinearLayout) mIndicatorView.findViewById(R.id.indicator_container);
                //arrow
                mArrowView = (SeekbarArrowView) mIndicatorView.findViewById(R.id.indicator_arrow);
                mArrowView.setColor(mIndicatorColor);
                //progressText
                mProgressTextView = (TextView) mIndicatorView.findViewById(R.id.isb_progress);
                mProgressTextView.setText(mSeekBar.getIndicatorTextString());
                mProgressTextView.setTextSize(Size.px2sp(mContext, mIndicatorTextSize));
                mProgressTextView.setTextColor(mIndicatorTextColor);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mTopContentView.setBackground(getGradientDrawable());
                } else {
                    mTopContentView.setBackgroundDrawable(getGradientDrawable());
                }
                 if (mIndicatorCustomTopContentView != null) {
                     int progressTextViewId = mContext.getResources().getIdentifier("isb_progress", "id", mContext.getApplicationContext().getPackageName());
                    View topContentView = mIndicatorCustomTopContentView;
                    if (progressTextViewId > 0) {
                        View tv = topContentView.findViewById(progressTextViewId);
                        if (tv != null && tv instanceof TextView) {
                            setTopContentView(topContentView, (TextView) tv);
                        } else {
                            setTopContentView(topContentView);
                        }
                    } else {
                        setTopContentView(topContentView);
                    }

                }
            }
        }
    }

    @NonNull
    private GradientDrawable getGradientDrawable() {
        GradientDrawable tvDrawable;
        if (mIndicatorType == SeekBarIndicatorType.ROUNDED_RECTANGLE) {
            tvDrawable = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.display_round_corner);
        } else {
            tvDrawable = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.display_square_corner);
        }
        tvDrawable.setColor(mIndicatorColor);
        return tvDrawable;
    }

    private int getWindowWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            return wm.getDefaultDisplay().getWidth();
        }
        return 0;
    }

    private int getIndicatorScreenX() {
        mSeekBar.getLocationOnScreen(mLocation);
        return mLocation[0];
    }

    private void adjustArrow(float touchX) {
        if (mIndicatorType == SeekBarIndicatorType.CUSTOM || mIndicatorType == SeekBarIndicatorType.CIRCULAR_BUBBLE) {
            return;
        }
        int indicatorScreenX = getIndicatorScreenX();
        if (indicatorScreenX + touchX < mIndicatorPopW.getContentView().getMeasuredWidth() / 2) {
            setMargin(mArrowView, -(int) (mIndicatorPopW.getContentView().getMeasuredWidth() / 2 - indicatorScreenX - touchX), -1, -1, -1);
        } else if (mWindowWidth - indicatorScreenX - touchX < mIndicatorPopW.getContentView().getMeasuredWidth() / 2) {
            setMargin(mArrowView, (int) (mIndicatorPopW.getContentView().getMeasuredWidth() / 2 - (mWindowWidth - indicatorScreenX - touchX)), -1, -1, -1);
        } else {
            setMargin(mArrowView, 0, 0, 0, 0);
        }
    }

    private void setMargin(View view, int left, int top, int right, int bottom) {
        if (view == null) {
            return;
        }
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.setMargins(left == -1 ? layoutParams.leftMargin : left, top == -1 ? layoutParams.topMargin : top, right == -1 ? layoutParams.rightMargin : right, bottom == -1 ? layoutParams.bottomMargin : bottom);
            view.requestLayout();
        }
    }

    void iniPop() {
        if (mIndicatorPopW != null) {
            return;
        }
        if (mIndicatorType != SeekBarIndicatorType.NONE && mIndicatorView != null) {
            mIndicatorView.measure(0, 0);
            mIndicatorPopW = new PopupWindow(mIndicatorView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
        }
    }

    View getInsideContentView() {
        return mIndicatorView;
    }

    void setProgressTextView(String text) {
        if (mIndicatorView instanceof CircleBubbleView) {
            ((CircleBubbleView) mIndicatorView).setProgress(text);
        } else if (mProgressTextView != null) {
            mProgressTextView.setText(text);
        }
    }

    void updateIndicatorLocation(int offset) {
        setMargin(mIndicatorView, offset, -1, -1, -1);
    }

    void updateArrowViewLocation(int offset) {
        setMargin(mArrowView, offset, -1, -1, -1);
    }
    void update(float touchX) {
        if (!mSeekBar.isEnabled() || !(mSeekBar.getVisibility() == View.VISIBLE)) {
            return;
        }
        refreshProgressText();
        if (mIndicatorPopW != null) {
            mIndicatorPopW.getContentView().measure(0, 0);
            mIndicatorPopW.update(mSeekBar, (int) (touchX - mIndicatorPopW.getContentView().getMeasuredWidth() / 2),
                    -(mSeekBar.getMeasuredHeight() + mIndicatorPopW.getContentView().getMeasuredHeight() - mSeekBar.getPaddingTop() /*- mSeekBar.getTextHeight() */ + mGap), -1, -1);
            adjustArrow(touchX);
        }
    }

    void show(float touchX) {
        if (!mSeekBar.isEnabled() || !(mSeekBar.getVisibility() == View.VISIBLE)) {
            return;
        }
        refreshProgressText();
        if (mIndicatorPopW != null) {
            mIndicatorPopW.getContentView().measure(0, 0);
            mIndicatorPopW.showAsDropDown(mSeekBar, (int) (touchX - mIndicatorPopW.getContentView().getMeasuredWidth() / 2f),
                    -(mSeekBar.getMeasuredHeight() + mIndicatorPopW.getContentView().getMeasuredHeight() - mSeekBar.getPaddingTop() /*- mSeekBar.getTextHeight()*/ + mGap));
            adjustArrow(touchX);
        }
    }

    void refreshProgressText() {
        String tickTextString = mSeekBar.getIndicatorTextString();
        if (mIndicatorView instanceof CircleBubbleView) {
            ((CircleBubbleView) mIndicatorView).setProgress(tickTextString);
        } else if (mProgressTextView != null) {
            mProgressTextView.setText(tickTextString);
        }
    }

    void hide() {
        if (mIndicatorPopW == null) {
            return;
        }
        mIndicatorPopW.dismiss();
    }

    boolean isShowing() {
        return mIndicatorPopW != null && mIndicatorPopW.isShowing();
    }

    public void setTopContentView(@NonNull View topContentView) {
        setTopContentView(topContentView, null);
    }

    public void setTopContentView(@NonNull View topContentView, @Nullable TextView progressTextView) {
        this.mProgressTextView = progressTextView;
        this.mTopContentView.removeAllViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            topContentView.setBackground(getGradientDrawable());
        } else {
            topContentView.setBackgroundDrawable(getGradientDrawable());
        }
        this.mTopContentView.addView(topContentView);
    }

}