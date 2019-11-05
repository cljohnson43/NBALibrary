package com.example.nbalibrary.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val TAG = "PlayersRepo"

class PlayersRepo {

    fun loadPlayers() {
        val db = FirebaseDatabase.getInstance().reference

        db.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "DB has ${snapshot.childrenCount} children")
            }
        })

    }

}