package com.example.nbalibrary.ui.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.nbalibrary.R
import com.example.nbalibrary.data.Player
import com.example.nbalibrary.databinding.FragmentPlayersListBinding
import com.example.nbalibrary.ui.adapters.PlayersAdapter
import com.example.nbalibrary.utils.obtainViewModel
import com.example.nbalibrary.viewmodels.PlayersViewModel

class PlayersFragment : Fragment() {

    private val viewModel by lazy {
        obtainViewModel(PlayersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPlayersListBinding>(
            inflater,
            R.layout.fragment_players_list,
            container,
            false
        )

        binding.rvPlayers.adapter = PlayersAdapter()
        viewModel.getPlayers().observe(this, object : Observer<List<Player>> {
            override fun onChanged(players: List<Player>?) {
                (binding.rvPlayers.adapter as PlayersAdapter).submitList(players)
            }
        })

        return binding.root
    }
}