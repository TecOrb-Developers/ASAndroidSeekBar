package com.seekbar

import android.content.Context
import android.util.TypedValue
import android.widget.TextView

class CommonClass(private  var context: Context) {
    
     fun getTextView(): TextView{
        val textView = TextView(context)
        val padding = dp2px(context, 16f)
        textView.setPadding(padding, padding, padding, 0)
        return textView
    }

    private fun dp2px(context: Context, dpValue: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue,
            context.resources.displayMetrics
        ).toInt()
    }
}