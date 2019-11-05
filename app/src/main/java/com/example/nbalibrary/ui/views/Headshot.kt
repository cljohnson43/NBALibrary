package com.example.nbalibrary.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.*
import android.graphics.RectF
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import android.net.Uri
import com.example.nbalibrary.R


class Headshot @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    private var mBitmap: Bitmap? = null
    private var mBitmapShader = Shader()
    private var mBitmapPaint = Paint()

    private var mShaderMatrix = Matrix()

    private var mBitmapDrawBounds = RectF()
    private var mStrokeBounds = RectF()

    private var mStrokePaint = Paint()

    init {
        var strokeColor = Color.TRANSPARENT
        var strokeWidth = 0f

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.Headshot, 0, 0)

            strokeColor = a.getColor(R.styleable.Headshot_strokeColor, Color.TRANSPARENT)
            strokeWidth =
                a.getDimensionPixelSize(R.styleable.Headshot_strokeWidth, 0).toFloat()

            a.recycle()
        }

        mShaderMatrix = Matrix()
        mBitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mStrokeBounds = RectF()
        mBitmapDrawBounds = RectF()
        mStrokePaint.color = strokeColor
        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.strokeWidth = strokeWidth

        setupBitmap()
    }

    private fun setupBitmap() {
        mBitmap = getBitmapFromDrawable(drawable)
        if (mBitmap == null) {
            return
        }

        mBitmapShader = BitmapShader(mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mBitmapPaint.setShader(mBitmapShader)

        updateBitmapSize()
    }

    protected fun updateCircleDrawBounds(bounds: RectF) {
        val contentWidth = (width - paddingLeft - paddingRight).toFloat()
        val contentHeight = (height - paddingTop - paddingBottom).toFloat()

        var left = paddingLeft.toFloat()
        var top = paddingTop.toFloat()
        if (contentWidth > contentHeight) {
            left += (contentWidth - contentHeight) / 2f
        } else {
            top += (contentHeight - contentWidth) / 2f
        }

        val diameter = Math.min(contentWidth, contentHeight)
        bounds.set(left, top, left + diameter, top + diameter)
    }

    private fun updateBitmapSize() {
        if (mBitmap == null) return
        else {
            val bitmap = mBitmap!!

            val dx: Float
            val dy: Float
            val scale: Float

            // scale up/down with respect to this view size and maintain aspect ratio
            // translate bitmap position with dx/dy to the center of the image
            if (bitmap.getWidth() < bitmap.getHeight()) {
                scale = mBitmapDrawBounds.width() / (bitmap.getWidth().toFloat())
                dx = mBitmapDrawBounds.left
                dy =
                    mBitmapDrawBounds.top - bitmap.getHeight() * scale / 2f + mBitmapDrawBounds.width() / 2f
            } else {
                scale = mBitmapDrawBounds.height() / (bitmap.getHeight().toFloat())
                dx =
                    mBitmapDrawBounds.left - bitmap.getWidth() * scale / 2f + mBitmapDrawBounds.width() / 2f
                dy = mBitmapDrawBounds.top
            }
            mShaderMatrix.setScale(scale, scale)
            mShaderMatrix.postTranslate(dx, dy)
            mBitmapShader.setLocalMatrix(mShaderMatrix)
        }
    }

    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
    override fun setImageResource(@DrawableRes resId: Int) {
        super.setImageResource(resId)
        setupBitmap()
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        setupBitmap()
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        setupBitmap()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        setupBitmap()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val halfStrokeWidth = mStrokePaint.strokeWidth / 2f
        updateCircleDrawBounds(mBitmapDrawBounds)
        mStrokeBounds.set(mBitmapDrawBounds)
        mStrokeBounds.inset(halfStrokeWidth, halfStrokeWidth)

        updateBitmapSize()
    }

    override fun onDraw(canvas: Canvas) {
        drawBitmap(canvas)
        drawStroke(canvas)
    }

    protected fun drawStroke(canvas: Canvas) {
        if (mStrokePaint.strokeWidth > 0f) {
            canvas.drawOval(mStrokeBounds, mStrokePaint)
        }
    }

    protected fun drawBitmap(canvas: Canvas) {
        canvas.drawOval(mBitmapDrawBounds, mBitmapPaint)
    }
}