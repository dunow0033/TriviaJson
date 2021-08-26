package com.example.triviajson_august9.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviajson_august9.model.remote.TriviaResponse
import com.example.triviajson_august9.model.repository.TriviaRepository
import com.example.triviajson_august9.model.wrapper.QuestionWithAnswer
import com.example.triviajson_august9.utils.Resource
import kotlinx.coroutines.launch

class TriviaViewModel(private val triviaRepository: TriviaRepository) : ViewModel() {

    private var _triviaQuestions: MutableLiveData<Resource<List<QuestionWithAnswer>>> =
        MutableLiveData()
    val triviaQuestions: LiveData<Resource<List<QuestionWithAnswer>>> = _triviaQuestions

    init {
        getTrivia()
    }

    private fun getTrivia() {
        Log.d(TAG, "getTrivia() called")
        viewModelScope.launch {
            val resource = triviaRepository.getTrivia()
            _triviaQuestions.postValue(resource)
        }
    }

    companion object {
        private val TAG = TriviaViewModel::class.java.name
    }
}