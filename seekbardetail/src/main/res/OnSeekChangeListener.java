package com.example.showseekbardetail;

import com.seekbardetail.DisplayingSeekBar;
import com.seekbardetail.SeekBarProperty;

public interface OnSeekChangeListener {
    void onSeeking(SeekBarProperty seekParams);
    void onStartTrackingTouch(DisplayingSeekBar seekBar);
    void onStopTrackingTouch(DisplayingSeekBar seekBar);
}