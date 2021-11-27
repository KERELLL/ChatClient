package com.example.customviewgroup.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.customviewgroup.R
import com.example.customviewgroup.model.Time
import com.example.customviewgroup.model.User
import com.example.customviewgroup.views.ChatGroup
import java.lang.IllegalStateException

class   RecyclerAdapter(val longClick: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    var data: MutableList<ListUserMessageItem> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int =
        when(data[position]){
            is User -> MESSAGE_VIEW_TYPE
            is Time -> TIME_VIEW_TYPE
            else -> throw IllegalStateException("Unknown item type: ${data[position].javaClass}")
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            MESSAGE_VIEW_TYPE -> {
                val layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0,0,0,15)
                val chatItem = ChatGroup(viewGroup.context)
                chatItem.layoutParams = layoutParams
                MessageViewHolder(chatItem)
            }
            TIME_VIEW_TYPE -> {
                DateViewHolder(
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.custom_view_item, viewGroup, false)
                )
            }
            else -> throw IllegalStateException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            MESSAGE_VIEW_TYPE -> {
                val userHolder = holder as MessageViewHolder
                userHolder.chatGroup.username= (data[position] as User).userNameData
                userHolder.chatGroup.message= (data[position] as User).userMessageData
                userHolder.chatGroup.reactions =  (data[position] as User).reactions

                holder.itemView.setOnLongClickListener{
                    longClick.invoke(position)
                    return@setOnLongClickListener true
                }
            }
            TIME_VIEW_TYPE -> {
                val timeHolder = holder as DateViewHolder
                timeHolder.itemDate.text = (data[position] as Time).sendTimeGet
            }
        }

    }



    inner class MessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var chatGroup: ChatGroup = itemView as ChatGroup
    }

    inner class DateViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var itemDate: TextView = itemView.findViewById(R.id.timeSend)
    }

    companion object{
        const val MESSAGE_VIEW_TYPE = 0
        const val TIME_VIEW_TYPE = 1
    }


}