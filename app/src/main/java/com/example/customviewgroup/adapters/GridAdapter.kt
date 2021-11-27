package com.example.customviewgroup.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.customviewgroup.R
import com.example.customviewgroup.utils.px

class GridAdapter(private val context: Context) : BaseAdapter() {

    private val emojis = mutableListOf<String>()

    init{
        fillEmojis()
    }


    override fun getCount(): Int {
        return emojis.size
    }

    override fun getItem(position: Int): Any {
        return emojis[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView : TextView
        if(convertView == null){
            // if it's not recycled, initialize some attributes
            textView = TextView(context)
            textView.layoutParams = ViewGroup.LayoutParams(50.px, 50.px)
            textView.textSize = 35f
            textView.setPadding(8, 8, 8, 8)
        }
        else{
            textView = convertView as TextView
        }
        textView.text = emojis[position]
        return textView
    }

    private fun fillEmojis() {
        for (i in 600 until 644) {
            emojis.add(String(Character.toChars(Integer.parseInt("1F${i}", 16))))
        }
    }
}