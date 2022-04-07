package com.example.vktestapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.vktestapp.databinding.FriendRowBinding
import com.example.vktestapp.entities.Friend

class FriendListViewHolder(
    private val binding: FriendRowBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(friend: Friend){
        binding.firstNameTv.text = friend.firstName
        binding.lastNameTv.text = friend.lastName
    }
}