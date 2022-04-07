package com.example.vktestapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vktestapp.databinding.FriendRowBinding
import com.example.vktestapp.entities.Friend

class FriendListAdapter(private var friendList: List<Friend> = listOf()) : RecyclerView.Adapter<FriendListViewHolder>() {

    fun updateFriendList(friendList: List<Friend>){
        this.friendList = friendList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListViewHolder {
        val holderRowBinding: FriendRowBinding = FriendRowBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return FriendListViewHolder(holderRowBinding)
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
        holder.bind(friendList[position])
    }

    override fun getItemCount(): Int {
        return friendList.size
    }
}