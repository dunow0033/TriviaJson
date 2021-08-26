package com.example.triviajson_august9.model.repository

import android.util.Log
import com.example.triviajson_august9.model.remote.ApiManager
import com.example.triviajson_august9.model.remote.TriviaResponse
import com.example.triviajson_august9.model.wrapper.QuestionWithAnswer
import com.example.triviajson_august9.utils.Resource

class TriviaRepository(private val apiManager: ApiManager) {

    suspend fun getTrivia(): Resource<List<QuestionWithAnswer>> {
        return try {
            val response = apiManager.getTrivia()
            if (response.isSuccessful && response.body() != null) {
                val newResponse = response.body()!!.copy().results.map {
                    QuestionWithAnswer(it.question, it.convertToAnswer())
                }

                Log.d(TAG, "getTrivia() success with ${response.body()}")
                Resource.Success(newResponse)
            } else {
                Log.d(TAG, "getTrivia() error")
                Resource.Error("Failed to get trivia.")
            }

        } catch (ex: Exception) {
            Log.d(TAG, "getTrivia() exception = $ex")
            return Resource.Error(ex.toString())
        }
    }

    companion object {
        private val TAG = TriviaRepository::class.java.name
    }
}