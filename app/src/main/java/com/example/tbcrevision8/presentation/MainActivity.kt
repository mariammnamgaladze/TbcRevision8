package com.example.tbcrevision8.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcrevision8.R
import com.example.tbcrevision8.common.ResponseHandler
import com.example.tbcrevision8.data.model.Random
import com.example.tbcrevision8.databinding.ActivityMainBinding
import com.example.tbcrevision8.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val randomAdapter = RandomAdapter()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observer()
        setUpRecycler()

    }

    private fun observer() {
        lifecycleScope.launch {
            mainViewModel.randomFlow.collect {
                if (it is ResponseHandler.Success) {
                    randomAdapter.submitList(it.response)
                }
            }
        }
    }

    private fun setUpRecycler() {

        binding.recyclerView.apply {
            adapter = randomAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        lifecycleScope.launch {
            mainViewModel.getRandom()
        }
    }
}