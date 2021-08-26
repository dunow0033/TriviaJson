package com.example.triviajson_august9.model.remote

import android.util.Log
import retrofit2.Response

val TAG = ApiManager::class.java.name

object ApiManager {

    private var service = RetrofitInstance.providesRetrofit().create(TriviaService::class.java)

    suspend fun getTrivia(
        count: Int = 1100,
        difficulty: String = "medium"
    ): Response<TriviaResponse> {
        Log.d(TAG, "getTrivia() called with count = $count difficulty = $difficulty")
        return service.getTrivia(count, difficulty)
    }
}