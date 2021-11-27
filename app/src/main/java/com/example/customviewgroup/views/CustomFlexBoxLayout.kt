    package com.example.customviewgroup.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.customviewgroup.R
import com.example.customviewgroup.databinding.ActivityMainBinding.inflate
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


    class CustomFlexBoxLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ) : ViewGroup(context, attrs, defStyleAttr) {
    var reactionsEmoji: MutableList<CustomEmojiView> = mutableListOf()
        set(value) {
            field = value
            requestLayout()
        }

        init {

        val btnAdd = Button(context).apply {
            text = "+"
            setTextColor(Color.WHITE)
            textSize = 20F
            gravity = Gravity.CENTER
            val scale = resources.displayMetrics.density
            val padding9dp = (1 * scale + 0.5f).toInt()
            val padding3dp = (8 * scale + 0.5f).toInt()
            val textSize = (16 * scale + 0.5f).toInt()

            setPadding(padding9dp, padding9dp, padding9dp, padding3dp)
            setBackgroundResource(R.drawable.custom_button)
            val marginLayoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            marginLayoutParams.setMargins(0,7   ,10,0)
            layoutParams = marginLayoutParams
        }
        val h =TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 37F, resources.displayMetrics).toInt()
        val w = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45F, resources.displayMetrics).toInt()
        btnAdd.layoutParams = ViewGroup.LayoutParams(w, h)
        btnAdd.setOnClickListener{
            setClick()
        }
        addView(btnAdd)

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var totalHeight = 0
        var totalWidth = 0
        var tmpW = 0

        val displayMetrics = resources.displayMetrics
        val widthOfScreen = displayMetrics.widthPixels - 20
        var heightUsed = 0
        val marginFromPic = 208


        for(i in 0 until childCount){
            val child = getChildAt(i)
            val marginRight = (child.layoutParams as MarginLayoutParams).rightMargin
            val marginLeft = (child.layoutParams as MarginLayoutParams).leftMargin

            measureChildWithMargins(child, widthMeasureSpec, tmpW, heightMeasureSpec, heightUsed)
            totalWidth += child.measuredWidth + marginRight + marginLeft
            totalHeight = maxOf(totalHeight, child.measuredHeight)
            tmpW += child.measuredWidth + marginRight + marginLeft
            if(tmpW + marginFromPic + child.measuredWidth> widthOfScreen){
                tmpW = 0
                totalHeight += child.measuredHeight
                heightUsed = totalHeight
            }
        }

        val resultWidth = resolveSize(totalWidth, widthMeasureSpec)
        val resultHeight = resolveSize(totalHeight , heightMeasureSpec)

        setMeasuredDimension(resultWidth, resultHeight)
    }
    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        var currentRight = 0
        var h = 0
        val marginFromPic = 208
        for(i in 0 until childCount){
            val child = getChildAt(i)
            val marginRight = (child.layoutParams as MarginLayoutParams).rightMargin
            val marginLeft = (child.layoutParams as MarginLayoutParams).leftMargin
            val displayMetrics = resources.displayMetrics
            val dpHeight = displayMetrics.heightPixels / displayMetrics.density
            val widthOfScreen = displayMetrics.widthPixels - 20

            if(currentRight + 208 + child.measuredWidth> widthOfScreen){
                currentRight = 0
                h+=child.measuredHeight
            }
            if(i == 0){
                child.layout(
                    currentRight + paddingLeft,
                    paddingTop + h,
                    currentRight + child.measuredWidth + marginRight + paddingRight + marginLeft,
                    child.measuredHeight + paddingEnd + h
                )
            }
            else{
                child.layout(
                    currentRight + marginRight + paddingLeft,
                    paddingTop + h,
                    currentRight + child.measuredWidth + marginRight + paddingRight,
                    child.measuredHeight + paddingBottom + h
                )
            }
            currentRight = child.right
        }
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


    private fun setClick() {

//        bottomSheetRoot.visibility = View.VISIBLE
//        val mBottomBehavior =
//            BottomSheetBehavior.from(bottomSheetRoot)
//        mBottomBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        val customEmojiView = CustomEmojiView(context).apply {
            text = (0 until 100).random().toString() + "\t" + "\uD83D\uDE00"
            val scale = resources.displayMetrics.density
            val padding9dp = (9 * scale + 0.5f).toInt()
            setPadding(padding9dp, padding9dp, padding9dp, padding9dp)
            setBackgroundResource(R.drawable.bg_custom_emoji_view)
            val marginLayoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            marginLayoutParams.setMargins(0,7   ,15,0)
            layoutParams = marginLayoutParams
        }
        reactionsEmoji.add(customEmojiView)
        addView(customEmojiView)
    }

}