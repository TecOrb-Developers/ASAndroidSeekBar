# IndicatorSeekBar

This is a customizable SeekBar library on Android. Also, If you don't need indicator and want to show tick texts to top of seek bar, please see [the other library](https://github.com/warkiz/TickSeekBar).

## Setup

```gradle
implementation 'com.github.warkiz.widget:indicatorseekbar:2.1.2'
```

## Usage
#### xml

```xml
<com.seekbardetail.DisplayingSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:isb_max="100"
    app:isb_min="-1.0"
    app:isb_progress="25"
    app:isb_seek_smoothly="true"
    app:isb_ticks_count="5"
    app:isb_show_tick_marks_type="oval"
    app:isb_tick_marks_size="13dp"
    app:isb_tick_marks_drawable="@mipmap/ic_launcher"
    app:isb_show_tick_texts="true"
    app:isb_tick_texts_size="15sp"
    app:isb_tick_texts_color="@color/color_blue"
    app:isb_thumb_color="@color/color_green"
    app:isb_thumb_size="20dp"
    app:isb_show_indicator="rounded_rectangle"
    app:isb_indicator_color="@color/color_gray"
    app:isb_indicator_text_color="@color/colorAccent"
    app:isb_indicator_text_size="18sp"
    app:isb_track_background_color="@color/color_gray"
    app:isb_track_background_size="2dp"
    app:isb_track_progress_color="@color/color_blue"
    app:isb_track_progress_size="4dp"
    app:isb_only_thumb_draggable="false"/>
```

```kotlin

     val continuous: DisplayingSeekBar = DisplayingSeekBar
            .with(context)
            .max(200F)
            .min(10F)
            .progress(33F)
            .tickCount(7)
            .showTickMarksType(TickMarkType.OVAL)
            .tickMarksColor(getResources().getColor(R.color.color_blue, null))
            .tickMarksSize(13)//dp
            .showTickTexts(true)
            .tickTextsColor(getResources().getColor(R.color.color_pink))
            .tickTextsSize(13)//sp
            .tickTextsTypeFace(Typeface.MONOSPACE)
            .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)  
            .indicatorColor(resources.getColor(R.color.black, null))
            .indicatorTextColor(resources.getColor(R.color.white,null))
            .showIndicatorType(IndicatorType.CIRCULAR_BUBBLE)
            .thumbColorStateList(resources.getColorStateList(R.color.selector_thumb_color, null))
            .thumbSize(14)
            .trackProgressColor(resources.getColor(R.color.color_blue, null))
            .trackProgressSize(4)
            .trackBackgroundColor(resources.getColor(R.color.color_gray, null))
            .trackBackgroundSize(2)
            .showThumbText(true)
            .thumbTextColor(resources.getColor(R.color.color_gray, null))
            .trackBackgroundSize(2)
            .onlyThumbDraggable(false)
            .build()

        continuous.onSeekChangeListener = object : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams) {}
            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar) {}
            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar) {}
        }
        content.addView(continuous)


 
                

```
## Indicator stay always

Put IndicatorSeekBar into a IndicatorStayLayout can make the indicator stayed always.
By the way, make sure you had called the attr to show the indicator before.

#### Xml

```xml
 <com.seekbardetail.DisplayLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <com.seekbardetail.DisplayingSeekBar
        android:id="@+id/percent_indicator"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:isb_indicator_color="@color/color_blue"
        app:isb_progress="76"
        app:isb_show_indicator="rectangle"
        app:isb_show_tick_texts="true"
        app:isb_tick_texts_color="@color/color_gray"
        app:isb_ticks_count="2"/>
    <!--show indicator can not be NONE-->
    ....../>
    <!--your layout-->
  
</com.seekbardetail.DisplayLayout>

```


