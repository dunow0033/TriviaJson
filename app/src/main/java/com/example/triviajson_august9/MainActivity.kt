package com.example.triviajson_august9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triviajson_august9.adapter.TriviaAdapter
import com.example.triviajson_august9.databinding.ActivityMainBinding
import com.example.triviajson_august9.model.remote.ApiManager
import com.example.triviajson_august9.model.repository.TriviaRepository
import com.example.triviajson_august9.ui.viewmodel.TriviaViewModel
import com.example.triviajson_august9.ui.viewmodel.TriviaViewModelFactory
import com.example.triviajson_august9.utils.Resource
import com.example.triviajson_august9.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: TriviaAdapter by lazy {
        TriviaAdapter()
    }

    private val viewModel: TriviaViewModel by viewModels {
        TriviaViewModelFactory(TriviaRepository(ApiManager))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            rvTrivia.apply {
                adapter = this@MainActivity.adapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            viewModel.triviaQuestions.observe(this@MainActivity, Observer { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        adapter.updateList(resource.item)
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                        showToast(resource.message)
                    }
                }
            })
        }
    }

}