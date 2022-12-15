package com.seekbardetail.Callback

import androidx.annotation.ColorInt

interface SeekBarColorCollector {
    fun collectSectionTrackColor(@ColorInt colorIntArr: IntArray?): Boolean
}