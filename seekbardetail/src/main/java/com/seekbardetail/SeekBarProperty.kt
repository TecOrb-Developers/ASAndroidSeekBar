package com.seekbardetail

class SeekBarProperty internal constructor(var seekBar: DisplayingSeekBar) {
    @JvmField
    var progress = 0
    @JvmField
    var progressFloat = 0f
    @JvmField
    var fromUser = false
    @JvmField
    var thumbPosition = 0
    @JvmField
    var tickText: String? = null
}