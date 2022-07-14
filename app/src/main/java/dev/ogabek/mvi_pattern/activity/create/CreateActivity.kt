package dev.ogabek.mvi_pattern.activity.create

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.ogabek.mvi_pattern.activity.create.helper.CreateHelperImpl
import dev.ogabek.mvi_pattern.activity.create.intent_state.CreateIntent
import dev.ogabek.mvi_pattern.activity.create.intent_state.CreateState
import dev.ogabek.mvi_pattern.activity.create.view_model.CreateViewModel
import dev.ogabek.mvi_pattern.activity.create.view_model.CreateViewModelFactory
import dev.ogabek.mvi_pattern.databinding.ActivityCreateBinding
import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.network.RetrofitBuilder
import kotlinx.coroutines.launch

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private lateinit var viewModel: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        observeViewModel()

    }

    private fun initViews() {

        val factory = CreateViewModelFactory(CreateHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[CreateViewModel::class.java]

        binding.btnCreate.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                intentCreatePost(Post(1, 1, title, description))
            }
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is CreateState.Init -> {
                        Log.d("CreateActivity", "Init")
                    }

                    is CreateState.Loading -> {
                        Log.d("CreateActivity", "Loading")
                    }

                    is CreateState.CreatePost -> {
                        Log.d("CreateActivity", "Create Post $it")
                        Toast.makeText(applicationContext, "Created", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    is CreateState.Error -> {
                        Log.d("CreateActivity", "Error $it")
                    }
                }
            }
        }
    }

    private fun intentCreatePost(post: Post) {
        viewModel.post = post
        lifecycleScope.launch {
            viewModel.createIntent.send(CreateIntent.CreatePost)
        }
    }

}