package com.example.nbalibrary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nbalibrary.R
import com.example.nbalibrary.data.Player
import com.example.nbalibrary.databinding.ListItemPlayerBinding

class PlayersAdapter : ListAdapter<Player, PlayersAdapter.PlayersViewHolder>(PlayerDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val binding = DataBindingUtil.inflate<ListItemPlayerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_player,
            parent,
            false
        )
        return PlayersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.binding.player = getItem(position)
    }

    class PlayersViewHolder(val binding: ListItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}

private class PlayerDiff : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem?.url == newItem?.url
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem?.firstName == newItem?.firstName &&
                oldItem?.lastName == newItem?.lastName &&
                oldItem?.hometown == newItem?.hometown
    }
}