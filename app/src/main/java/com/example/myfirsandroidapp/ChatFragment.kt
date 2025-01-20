package com.example.myfirsandroidapp

import android.os.Bundle
import android.view.View
import com.example.myfirsandroidapp.databinding.FragmentChatBinding

class ChatFragment : BaseFragment<FragmentChatBinding>() {


    private lateinit var adapter: ChatListAdapter

    override fun getLayoutId(): Int = R.layout.fragment_chat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChatListAdapter()
    }
}