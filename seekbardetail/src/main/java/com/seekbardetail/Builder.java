package com.seekbardetail;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.seekbardetail.Callback.SeekBarIndicatorType;
import com.seekbardetail.Callback.TickMarkType;
import com.seekbardetail.Utils.Size;

public class Builder {
    final Context context;
    //seek bar
    float max = 100;
    float min = 0;
    float progress = 0;
    boolean progressValueFloat = false;
    boolean seekSmoothly = false;
    boolean r2l = false;
    boolean userSeekable = true;
    boolean onlyThumbDraggable = false;
    boolean clearPadding = false;
    //indicator
    int showIndicatorType = SeekBarIndicatorType.ROUNDED_RECTANGLE;
    int indicatorColor = Color.parseColor("#325D6A");
    int indicatorTextColor = Color.parseColor("#FFFFFF");
    int indicatorTextSize = 0;
    View indicatorContentView = null;
    View indicatorTopContentView = null;
    //track
    int trackBackgroundSize = 0;
    int trackBackgroundColor = Color.parseColor("#F4D3D3");
    int trackProgressSize = 0;
    int trackProgressColor = Color.parseColor("#325D6A");
    boolean trackRoundedCorners = false;
    //thumbText
    int thumbTextColor = Color.parseColor("#325D6A");
    boolean showThumbText = false;
    //thumb
    int thumbSize = 0;
    int thumbColor = Color.parseColor("#325D6A");
    ColorStateList thumbColorStateList = null;
    Drawable thumbDrawable = null;
    //tickTexts
    boolean showTickText;
    int tickTextsColor = Color.parseColor("#325D6A");
    int tickTextsSize = 0;
    String[] tickTextsCustomArray = null;
    Typeface tickTextsTypeFace = Typeface.DEFAULT;
    ColorStateList tickTextsColorStateList = null;
    //tickMarks
    int tickCount = 0;
    int showTickMarksType = TickMarkType.NONE;
    int tickMarksColor = Color.parseColor("#827579");
    int tickMarksSize = 0;
    Drawable tickMarksDrawable = null;
    boolean tickMarksEndsHide = false;
    boolean tickMarksSweptHide = false;
    ColorStateList tickMarksColorStateList = null;

    Builder(Context context) {
        this.context = context;
        this.indicatorTextSize = Size.sp2px(context, 14);
        this.trackBackgroundSize = Size.dp2px(context, 2);
        this.trackProgressSize = Size.dp2px(context, 2);
        this.tickMarksSize = Size.dp2px(context, 10);
        this.tickTextsSize = Size.sp2px(context, 13);
        this.thumbSize = Size.dp2px(context, 14);
    }

    public DisplayingSeekBar build() {
        return new DisplayingSeekBar(this);
    }

    public Builder max(float max) {
        this.max = max;
        return this;
    }

    public Builder min(float min) {
        this.min = min;
        return this;
    }

    public Builder progress(float progress) {
        this.progress = progress;
        return this;
    }

    public Builder progressValueFloat(boolean isFloatProgress) {
        this.progressValueFloat = isFloatProgress;
        return this;
    }

    public Builder seekSmoothly(boolean seekSmoothly) {
        this.seekSmoothly = seekSmoothly;
        return this;
    }

    public Builder r2l(boolean r2l) {
        this.r2l = r2l;
        return this;
    }
    public Builder clearPadding(boolean clearPadding) {
        this.clearPadding = clearPadding;
        return this;
    }

    public Builder userSeekable(boolean userSeekable) {
        this.userSeekable = userSeekable;
        return this;
    }

    public Builder onlyThumbDraggable(boolean onlyThumbDraggable) {
        this.onlyThumbDraggable = onlyThumbDraggable;
        return this;
    }

    public Builder showIndicatorType(int showIndicatorType) {
        this.showIndicatorType = showIndicatorType;
        return this;
    }

    public Builder indicatorColor(@ColorInt int indicatorColor) {
        this.indicatorColor = indicatorColor;
        return this;
    }
    public Builder indicatorTextColor(@ColorInt int indicatorTextColor) {
        this.indicatorTextColor = indicatorTextColor;
        return this;
    }

    public Builder trackBackgroundSize(int trackBackgroundSize) {
        this.trackBackgroundSize = Size.dp2px(context, trackBackgroundSize);
        return this;
    }

    public Builder trackBackgroundColor(@ColorInt int trackBackgroundColor) {
        this.trackBackgroundColor = trackBackgroundColor;
        return this;
    }

    public Builder trackProgressSize(int trackProgressSize) {
        this.trackProgressSize = Size.dp2px(context, trackProgressSize);
        return this;
    }

    public Builder trackProgressColor(@ColorInt int trackProgressColor) {
        this.trackProgressColor = trackProgressColor;
        return this;
    }

    public Builder trackRoundedCorners(boolean trackRoundedCorners) {
        this.trackRoundedCorners = trackRoundedCorners;
        return this;
    }

    public Builder thumbTextColor(@ColorInt int thumbTextColor) {
        this.thumbTextColor = thumbTextColor;
        return this;
    }

    public Builder showThumbText(boolean showThumbText) {
        this.showThumbText = showThumbText;
        return this;
    }

    public Builder thumbColor(@ColorInt int thumbColor) {
        this.thumbColor = thumbColor;
        return this;
    }

    public Builder thumbColorStateList(@NonNull ColorStateList thumbColorStateList) {
        this.thumbColorStateList = thumbColorStateList;
        return this;
    }

    public Builder thumbSize(int thumbSize) {
        this.thumbSize = Size.dp2px(context, thumbSize);
        return this;
    }

    public Builder thumbDrawable(@NonNull Drawable thumbDrawable) {
        this.thumbDrawable = thumbDrawable;
        return this;
    }

    public Builder showTickTexts(boolean showTickText) {
        this.showTickText = showTickText;
        return this;
    }

    public Builder tickCount(int tickCount) {
        this.tickCount = tickCount;
        return this;
    }
public Builder showTickMarksType(int tickMarksType) {
        this.showTickMarksType = tickMarksType;
        return this;
    }


}