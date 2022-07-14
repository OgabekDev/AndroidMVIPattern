package dev.ogabek.mvi_pattern.activity.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.ogabek.mvi_pattern.activity.update.helper.UpdateHelperImpl
import dev.ogabek.mvi_pattern.activity.update.intent_state.UpdateIntent
import dev.ogabek.mvi_pattern.activity.update.intent_state.UpdateState
import dev.ogabek.mvi_pattern.activity.update.view_model.UpdateViewModel
import dev.ogabek.mvi_pattern.activity.update.view_model.UpdateViewModelFactory
import dev.ogabek.mvi_pattern.databinding.ActivityUpdateBinding
import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.network.RetrofitBuilder
import kotlinx.coroutines.launch

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var viewModel: UpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        observeViewModel()

    }

    private fun initViews() {

        val factory = UpdateViewModelFactory(UpdateHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[UpdateViewModel::class.java]

        val post: Post = intent.getSerializableExtra("post") as Post
        binding.apply {
            etTitle.setText(post.title)
            etDescription.setText(post.body)
        }

        binding.btnUpdate.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                intentUpdatePost(post.id, Post(post.id, post.userId, title, description))
            }
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is UpdateState.Init -> {
                        Log.d("UpdateActivity", "Init")
                    }

                    is UpdateState.Loading -> {
                        Log.d("UpdateActivity", "Loading")
                    }

                    is UpdateState.UpdatePost -> {
                        Log.d("UpdateActivity", "Update Post $it")
                        Toast.makeText(applicationContext, "Updated", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    is UpdateState.Error -> {
                        Log.d("UpdateActivity", "Error $it")
                    }
                }
            }
        }
    }

    private fun intentUpdatePost(id:Int, post: Post) {
        viewModel.id = id
        viewModel.post = post
        lifecycleScope.launch {
            viewModel.updateIntent.send(UpdateIntent.UpdatePost)
        }
    }
}