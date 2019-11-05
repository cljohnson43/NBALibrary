package com.example.nbalibrary.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.nbalibrary.R

private val STROKE_WIDTH_PERCENT = 0.25f

class TeamBackdrop @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPrimaryPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mSecondaryPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        if (attrs != null) {
            val arr = context.obtainStyledAttributes(attrs, R.styleable.TeamBackdrop, 0, 0)
            val primaryColor = arr.getColor(R.styleable.TeamBackdrop_teamPrimaryColor, Color.BLACK)
            val secondaryColor = arr.getColor(R.styleable.TeamBackdrop_teamSecondaryColor, Color.WHITE)

            mPrimaryPaint.color = primaryColor
            mPrimaryPaint.style = Paint.Style.FILL

            mSecondaryPaint.color = secondaryColor
            mSecondaryPaint.style = Paint.Style.STROKE

            arr.recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val strokeWidth = w * STROKE_WIDTH_PERCENT
        mSecondaryPaint.strokeWidth = strokeWidth
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(left.toFloat(), bottom.toFloat(), right.toFloat(), top.toFloat(), mSecondaryPaint)
        canvas?.drawPaint(mPrimaryPaint)
    }
}