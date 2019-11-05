package com.example.nbalibrary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nbalibrary.data.Player
import com.example.nbalibrary.data.PlayersRepo

class PlayersViewModel : ViewModel() {

    private val repo: PlayersRepo by lazy {
        PlayersRepo()
    }

    init {
        repo.loadPlayers()
    }

    fun getPlayers(): LiveData<List<Player>> = repo.players
}