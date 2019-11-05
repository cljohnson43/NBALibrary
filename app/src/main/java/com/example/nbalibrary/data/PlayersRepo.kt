package com.example.nbalibrary.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

private const val TAG = "PlayersRepo"

class PlayersRepo {

    private val _players: MutableLiveData<List<Player>> by lazy {
        MutableLiveData<List<Player>>()
    }
    val players: LiveData<List<Player>> = _players

    fun loadPlayers() {
        val db = FirebaseDatabase.getInstance().reference

        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val players = mutableListOf<Player>()
                snapshot.children.forEach {
                    val player = it.getValue(Player::class.java)
                    if (player != null) players.add(player)
                }
                _players.value = players
            }
        })

    }
}

