package com.seekbardetail.Callback

import com.seekbardetail.SeekBarProperty
import com.seekbardetail.DisplayingSeekBar

interface OnSeekBarChangeListener {
    fun onSeeking(seekParams: SeekBarProperty?)
    fun onStartTrackingTouch(seekBar: DisplayingSeekBar?)
    fun onStopTrackingTouch(seekBar: DisplayingSeekBar?)
}