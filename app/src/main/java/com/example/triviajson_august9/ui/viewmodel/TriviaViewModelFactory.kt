package com.example.triviajson_august9.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.triviajson_august9.model.repository.TriviaRepository
import java.lang.IllegalArgumentException

class TriviaViewModelFactory(
    private val triviaRepository: TriviaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaViewModel::class.java)) {
            return TriviaViewModel(triviaRepository) as T
        } else {
            throw IllegalArgumentException("Could not create instance of TriviaViewModel")
        }
    }
}