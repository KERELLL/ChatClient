package com.example.customviewgroup.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import com.example.customviewgroup.R
import com.example.customviewgroup.model.User


class ChatGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private lateinit var un: TextView
    private lateinit var um: TextView

    var user: User? = null
        set(value) {
         field = value
            un = findViewById(R.id.userName)
            um =findViewById(R.id.userMes)
            un.text = user?.userNameData ?: "Unknown"
            um.text = user?.userMessageData ?: "Empty"

        }

    var username: String = ""
        set(value) {
            field = value
            un = findViewById(R.id.userName)
            un.text = username
            requestLayout()
        }

    var message: String = ""
        set(value) {
            field = value
            um =findViewById(R.id.userMes)
            um.text = message
            requestLayout()
        }

    var reactions: MutableList<CustomEmojiView> = mutableListOf()
        set(value) {
            field = value
            requestLayout()
        }

    init {
        inflate(context, R.layout.custom_view_group_layout, this)
    }


    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val view = getChildAt(0)
        val imageView = getChildAt(1)
        val userName = getChildAt(2)
        val userMessage = getChildAt(3)
        var flexBoxLayout = getChildAt(4) as CustomFlexBoxLayout

        var totalWidth = 0
        var totalHeight = 0


        measureChildWithMargins(imageView, widthMeasureSpec, 0, heightMeasureSpec, 0)

        val marginLeft = (imageView.layoutParams as MarginLayoutParams).leftMargin
        val marginRight = (imageView.layoutParams as MarginLayoutParams).rightMargin
        val marginTop = (imageView.layoutParams as MarginLayoutParams).topMargin
        totalWidth += imageView.measuredWidth + marginLeft + marginRight + marginTop
        totalHeight = maxOf(totalHeight, imageView.measuredHeight)
        measureChildWithMargins(
            userName,
            widthMeasureSpec,
            imageView.measuredWidth,
            heightMeasureSpec,
            0
        )


        totalHeight = maxOf(totalHeight, userName.measuredHeight)
        measureChildWithMargins(
            userMessage,
            widthMeasureSpec,
            imageView.measuredWidth,
            heightMeasureSpec,
            0
        )


        totalWidth += maxOf(userMessage.measuredWidth, userName.measuredWidth)
        totalHeight = maxOf(totalHeight, userMessage.measuredHeight + userName.measuredHeight)

        measureChildWithMargins(
            view,
            widthMeasureSpec,
            maxOf(userMessage.measuredWidth, userName.measuredWidth),
            heightMeasureSpec,
            0
        )

        val tmp = flexBoxLayout
        for(reaction in reactions){
            tmp.removeView(reaction)
        }
        for(reaction in reactions){
            tmp.addView(reaction)
        }

        flexBoxLayout = tmp


        measureChildWithMargins(
            flexBoxLayout,
            widthMeasureSpec,
            imageView.measuredWidth,
            heightMeasureSpec,
            userMessage.measuredHeight + userName.measuredHeight
        )

        totalWidth += maxOf(totalWidth, flexBoxLayout.measuredWidth)
        totalHeight = maxOf(totalHeight, userMessage.measuredHeight + userName.measuredHeight + flexBoxLayout.measuredHeight)
        val resultWidth = resolveSize(totalWidth + paddingRight + paddingLeft, widthMeasureSpec)
        val resultHeight = resolveSize(totalHeight + paddingBottom + paddingTop, heightMeasureSpec)

        setMeasuredDimension(resultWidth, resultHeight)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        val view = getChildAt(0)
        val imageView = getChildAt(1)
        val userName = getChildAt(2)
        val userMessage = getChildAt(3)
        val flexBoxLayout = getChildAt(4)
        val marginRight = (imageView.layoutParams as MarginLayoutParams).rightMargin
        val marginLeft = (imageView.layoutParams as MarginLayoutParams).leftMargin
        val marginTop = (imageView.layoutParams as MarginLayoutParams).topMargin
        imageView.layout(
            paddingLeft,
            paddingTop,
            paddingLeft + imageView.measuredWidth,
            paddingTop + imageView.measuredHeight
        )

        userName.layout(
            imageView.right + marginRight,
            paddingTop,
            imageView.right + userName.measuredWidth + marginRight,
            paddingTop + userName.measuredHeight
        )

        userMessage.layout(
            imageView.right + marginRight,
            userName.height + paddingTop,
            imageView.right + userMessage.measuredWidth + marginRight,
            userMessage.measuredHeight + userName.height + paddingTop
        )

        view.layout(
            imageView.right + marginRight,
            paddingTop,
            imageView.right + maxOf(userMessage.measuredWidth, userName.measuredWidth) + marginRight,
            userMessage.measuredHeight + userName.height + paddingTop
        )


        flexBoxLayout.layout(
            imageView.right + marginRight,
            +userName.height + userMessage.height,
            imageView.right + marginRight + flexBoxLayout.measuredWidth,
            userName.height + userMessage.height + paddingTop + flexBoxLayout.measuredHeight + marginTop
        )
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun checkLayoutParams(p: LayoutParams): Boolean {
        return p is MarginLayoutParams
    }

    override fun generateLayoutParams(p: LayoutParams): LayoutParams {
        return MarginLayoutParams(p)
    }




}