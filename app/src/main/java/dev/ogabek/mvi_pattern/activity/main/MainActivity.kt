package dev.ogabek.mvi_pattern.activity.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ogabek.mvi_pattern.activity.create.CreateActivity
import dev.ogabek.mvi_pattern.activity.main.helper.MainHelperImpl
import dev.ogabek.mvi_pattern.activity.main.intent_state.MainIntent
import dev.ogabek.mvi_pattern.activity.main.intent_state.MainState
import dev.ogabek.mvi_pattern.activity.main.view_model.MainViewModel
import dev.ogabek.mvi_pattern.activity.main.view_model.MainViewModelFactory
import dev.ogabek.mvi_pattern.adapter.PostAdapter
import dev.ogabek.mvi_pattern.databinding.ActivityMainBinding
import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.network.RetrofitBuilder
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        observeViewModel()

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainState.Init -> {
                        Log.d("MainActivity", "Init")
                    }

                    is MainState.Loading -> {
                        Log.d("MainActivity", "Loading")
                    }

                    is MainState.AllPosts -> {
                        Log.d("MainActivity", "All Posts ${it.posts}")
                        refreshAdapter(it.posts)
                    }

                    is MainState.DeletePost -> {
                        Log.d("MainActivity", "Delete Post $it")
                        intentAllPosts()
                    }

                    is MainState.Error -> {
                        Log.d("MainActivity", "Error $it")
                    }

                }
            }
        }
    }

    private fun refreshAdapter(list: ArrayList<Post>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = PostAdapter(this, list)
    }

    private fun initViews() {

        val factory = MainViewModelFactory(MainHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        intentAllPosts()

        binding.btnCreate.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }

    }

    private fun intentAllPosts() {
        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.AllPosts)
        }
    }

    fun intentDeletePost(id: Int) {
        viewModel.postId = id
        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.DeletePost)
        }
    }


}