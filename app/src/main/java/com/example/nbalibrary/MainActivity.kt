package com.example.nbalibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nbalibrary.data.PlayersRepo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PlayersRepo().loadPlayers()
    }
}
