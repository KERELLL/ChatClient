package com.example.customviewgroup.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.example.customviewgroup.R

class CustomEmojiView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr) {

    var text = (0 until 100).random().toString()
        set(value) {
            field = value
            requestLayout()
        }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        textAlign = Paint.Align.CENTER
    }

    private val textBound = Rect()
    private val textCoordinate = PointF()
    private val tempFontMetrics = Paint.FontMetrics()

    init {
        val allAttrs = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomEmojiView,
            defStyleAttr,
            defStyleRes
        )
        text += "\t" + allAttrs.getString(R.styleable.CustomEmojiView_customText).orEmpty()
        textPaint.color =
            allAttrs.getColor(R.styleable.CustomEmojiView_customTextColor, Color.WHITE)
        allAttrs.recycle()
        val scale = resources.displayMetrics.density
        val padding9dp = (9 * scale + 0.5f).toInt()
        val textSize = (16 * scale + 0.5f).toInt()
        textPaint.textSize = textSize.toFloat()
        setPadding(padding9dp, padding9dp, padding9dp, padding9dp)
        val marginLayoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        marginLayoutParams.setMargins(0,7   ,10,0)
        layoutParams = marginLayoutParams
        setOnClickListener {
            setClick(this)
            println("Pressed emoji *)")
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        textPaint.getTextBounds(text, 0, text.length, textBound)

        val textH = textBound.height()
        val textW = textBound.width()
        val totalH = textH + paddingTop + paddingBottom
        val totalW = textW + paddingRight + paddingLeft


        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val resultW = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.UNSPECIFIED -> totalW
            MeasureSpec.AT_MOST -> if (widthSize < totalW) widthSize else totalW
            else -> throw IllegalStateException()
        }

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val resultH = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.UNSPECIFIED -> totalH
            MeasureSpec.AT_MOST -> if (heightSize < totalH) heightSize else totalH
            else -> throw IllegalStateException()
        }

        setMeasuredDimension(resultW, resultH)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + SUPPORTED_DRAWABLE_STATE.size)
        if (isSelected) {
            mergeDrawableStates(drawableState, SUPPORTED_DRAWABLE_STATE)
        }
        return drawableState
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        textPaint.getFontMetrics(tempFontMetrics)
        textCoordinate.x = w / 2f
        textCoordinate.y = h / 2f + textBound.height() / 2 - tempFontMetrics.descent
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText(text, textCoordinate.x, textCoordinate.y, textPaint)
    }

    companion object {
        private val SUPPORTED_DRAWABLE_STATE = intArrayOf(android.R.attr.state_selected)
    }

    private fun setClick(customEmojiView: CustomEmojiView) {
        customEmojiView.isSelected = !customEmojiView.isSelected
        val x = customEmojiView.text.split("\t")
        if (customEmojiView.isSelected) {
            customEmojiView.text = (x[0].toInt() + 1).toString() + "\t" + x[1]

        } else {
            customEmojiView.text = (x[0].toInt() - 1).toString() + "\t" + x[1]
        }
    }

}