package com.seekbar

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.seekbardetail.*
import com.seekbardetail.Callback.SeekBarIndicatorType
import com.seekbardetail.Callback.OnSeekBarChangeListener
import com.seekbardetail.Callback.TickMarkType
import com.seekbardetail.DisplayingSeekBar
import com.seekbardetail.DisplayLayout
import com.seekbardetail.SeekBarProperty


class MainActivity : AppCompatActivity() {
    private lateinit var context: Context

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        val content = findViewById<View>(R.id.layout) as LinearLayout

        //custom indicator text
        val percentIndicator: DisplayingSeekBar = findViewById(R.id.percent_indicator)
        percentIndicator.setIndicatorTextFormat("\${PROGRESS} %")

        val txtSbView: TextView = CommonClass(context).getTextView()
        txtSbView.text = getString(R.string.txt_seekbar_view)
        content.addView(txtSbView)
        val continuous: DisplayingSeekBar = DisplayingSeekBar
            .with(context)
            .max(200F)
            .min(10F)
            .progress(33F)
            .indicatorColor(resources.getColor(R.color.black, null))
            .indicatorTextColor(resources.getColor(R.color.white, null))
            .showIndicatorType(SeekBarIndicatorType.CIRCULAR_BUBBLE)
            .thumbColorStateList(resources.getColorStateList(R.color.selector_thumb_color, null))
            .thumbSize(14)
            .trackProgressColor(resources.getColor(R.color.color_blue, null))
            .trackProgressSize(4)
            .trackBackgroundColor(resources.getColor(R.color.color_gray, null))
            .trackBackgroundSize(2)
            .showThumbText(true)
            .thumbTextColor(resources.getColor(R.color.color_gray, null))
            .build()
        continuous.onSeekChangeListener = object : OnSeekBarChangeListener {
            override fun onSeeking(seekParams: SeekBarProperty?) {}
            override fun onStartTrackingTouch(seekBar: DisplayingSeekBar?) {}
            override fun onStopTrackingTouch(seekBar: DisplayingSeekBar?) {}

        }
        content.addView(continuous)


        val txtThumbChange: TextView = CommonClass(context).getTextView()
        txtThumbChange.text = getString(R.string.thumb_chnages_txt)
        content.addView(txtThumbChange)
        val continuous2TickTexts = DisplayingSeekBar
            .with(context)
            .max(100f)
            .min(10F)
            .progress(33F)
            .tickCount(2)
            .showTickMarksType(TickMarkType.DIVIDER)
            .showTickTexts(true)
            .indicatorColor(resources.getColor(R.color.color_gray, null))
            .indicatorTextColor(Color.parseColor("#ffffff"))
            .showIndicatorType(SeekBarIndicatorType.RECTANGLE)
            .thumbDrawable(resources.getDrawable(R.drawable.selector_thumb_drawable, null))
            .thumbSize(18)
            .trackProgressColor(resources.getColor(R.color.colorAccent, null))
            .trackProgressSize(4)
            .trackBackgroundColor(resources.getColor(R.color.color_gray, null))
            .trackBackgroundSize(2)
            .build()

        val continuousStayLayout =
            DisplayLayout(context)
        continuousStayLayout.attachTo(continuous2TickTexts)
        content.addView(continuousStayLayout)

        val txtThumbRipple: TextView = CommonClass(context).getTextView()
        txtThumbRipple.text = getString(R.string.custom_ripple_txt)
        content.addView(txtThumbRipple)
        val continuous_texts_ends_custom_thumb = DisplayingSeekBar
            .with(context)
            .max(100f)
            .min(30f)
            .progress(33f)
            .tickCount(2)
            .showTickMarksType(TickMarkType.NONE)
            .showTickTexts(true)
            .indicatorColor(resources.getColor(R.color.color_blue, null))
            .indicatorTextColor(Color.parseColor("#ffffff"))
            .showIndicatorType(SeekBarIndicatorType.CIRCULAR_BUBBLE)
            .thumbDrawable(resources.getDrawable(R.drawable.selector_thumb_ripple_drawable, null))
            .thumbSize(26)
            .trackProgressColor(resources.getColor(R.color.colorAccent, null))
            .trackProgressSize(4)
            .trackBackgroundColor(resources.getColor(R.color.color_gray, null))
            .trackBackgroundSize(2)
            .build()
        content.addView(continuous_texts_ends_custom_thumb)


    }


}

