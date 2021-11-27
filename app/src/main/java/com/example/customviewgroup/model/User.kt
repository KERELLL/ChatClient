package com.example.customviewgroup.model

import com.example.customviewgroup.adapters.ListUserMessageItem
import com.example.customviewgroup.views.CustomEmojiView

class User(var userName: String, var userMessage: String, var userImage: Int, var reactionData: MutableList<CustomEmojiView>) :
    ListUserMessageItem {

    var userImageData: Int = userImage

    var userNameData: String = userName

    var userMessageData: String = userMessage

    var reactions : MutableList<CustomEmojiView> = reactionData

}