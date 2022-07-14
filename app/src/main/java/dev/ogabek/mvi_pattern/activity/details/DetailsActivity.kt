package dev.ogabek.mvi_pattern.activity.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.ogabek.mvi_pattern.activity.details.helper.DetailsHelperImpl
import dev.ogabek.mvi_pattern.activity.details.intent_state.DetailsIntent
import dev.ogabek.mvi_pattern.activity.details.intent_state.DetailsState
import dev.ogabek.mvi_pattern.activity.details.view_model.DetailsViewModel
import dev.ogabek.mvi_pattern.activity.details.view_model.DetailsViewModelFactory
import dev.ogabek.mvi_pattern.activity.update.UpdateActivity
import dev.ogabek.mvi_pattern.databinding.ActivityDetailsBinding
import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.network.RetrofitBuilder
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        observeViewModel()

    }

    private fun initViews() {

        val factory = DetailsViewModelFactory(DetailsHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[DetailsViewModel::class.java]

        intentDetailsPost(intent.getIntExtra("id", 0))

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("post", post)
            startActivity(intent)
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is DetailsState.Init -> {
                        Log.d("DetailsActivity", "Init")
                    }

                    is DetailsState.Loading -> {
                        Log.d("DetailsActivity", "Loading")
                    }

                    is DetailsState.DetailsPost -> {
                        Log.d("DetailsActivity", "Details Post $it")
                        Toast.makeText(applicationContext, "Ready to view", Toast.LENGTH_SHORT).show()
                        binding.apply {
                            etTitle.text = it.post.title
                            etDescription.text = it.post.body
                        }
                        post = it.post
                    }

                    is DetailsState.Error -> {
                        Log.d("UpdateActivity", "Error $it")
                    }
                }
            }
        }
    }

    private fun intentDetailsPost(id:Int) {
        viewModel.id = id
        lifecycleScope.launch {
            viewModel.detailsIntent.send(DetailsIntent.DetailsPost)
        }
    }
}