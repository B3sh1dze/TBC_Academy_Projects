package com.example.myfirsandroidapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    private val _chatItems = MutableLiveData<List<ChatItem>>()
    val chatItems: LiveData<List<ChatItem>> get() = _chatItems

    init {
        loadChatData()
    }

    private fun loadChatData() {
        val data = listOf(
            ChatItem(1, "https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg", "გრიშა ონიანი", "თავის ტერიტორიას ბომბავდა", "4:20 PM", 3, false, MessageType.TEXT),
            ChatItem(2, null, "ჯემალ კაკაურიძე", "შემოგევლე", "3:00 AM", 0, true, MessageType.VOICE),
            ChatItem(3, "https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg", "გურამ ჯინორია", "ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა", "1:00", 0, false, MessageType.FILE),
            ChatItem(4, "", "კაკო წენგუაშვილი", "ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი", "1:00 PM", 0, false, MessageType.TEXT)
        )
        _chatItems.value = data
    }
}