package com.example.nbalibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.nbalibrary.data.PlayersRepo
import com.example.nbalibrary.viewmodels.PlayersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val viewModel: PlayersViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PlayersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getPlayers()
    }
}
