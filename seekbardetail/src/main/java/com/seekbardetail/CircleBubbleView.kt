package com.seekbardetail

import android.content.Context
import android.graphics.*
import com.seekbardetail.Utils.Size.dp2px
import android.view.View

class CircleBubbleView(
    private val mContext: Context,
    private val mIndicatorTextSize: Float,
    private val mIndicatorTextColor: Int,
    private val mIndicatorColor: Int,
    maxLengthText: String
) : View(
    mContext, null, 0
) {
    private var mPath: Path? = null
    private var mPaint: Paint? = null
    private var mIndicatorWidth = 0f
    private var mIndicatorHeight = 0f
    private var mTextHeight = 0f
    private var mProgress: String? = null

    init {
        init(maxLengthText)
    }

    private fun init(maxLengthText: String) {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.strokeWidth = 1f
        mPaint!!.textAlign = Paint.Align.CENTER
        mPaint!!.textSize = mIndicatorTextSize
        val mRect = Rect()
        mPaint!!.getTextBounds(maxLengthText, 0, maxLengthText.length, mRect)
        mIndicatorWidth = (mRect.width() + dp2px(mContext, 4f)).toFloat()
        val minWidth = dp2px(mContext, 36f)
        if (mIndicatorWidth < minWidth) {
            mIndicatorWidth = minWidth.toFloat()
        }
        mTextHeight = mRect.height().toFloat()
        mIndicatorHeight = mIndicatorWidth * 1.2f
        initPath()
    }

    private fun initPath() {
        mPath = Path()
        val rectF = RectF(0F, 0F, mIndicatorWidth, mIndicatorWidth)
        mPath!!.arcTo(rectF, 135F, 270F)
        mPath!!.lineTo(mIndicatorWidth / 2, mIndicatorHeight)
        mPath!!.close()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mIndicatorWidth.toInt(), mIndicatorHeight.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        mPaint!!.color = mIndicatorColor
        canvas.drawPath(mPath!!, mPaint!!)
        mPaint!!.color = mIndicatorTextColor
        canvas.drawText(
            mProgress!!,
            mIndicatorWidth / 2f,
            mIndicatorHeight / 2 + mTextHeight / 4,
            mPaint!!
        )
    }

    fun setProgress(progress: String?) {
        mProgress = progress
        invalidate()
    }
}