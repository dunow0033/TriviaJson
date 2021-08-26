package com.example.triviajson_august9.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaService {

    @GET("/api.php")
    suspend fun getTrivia(
        @Query("amount") amount: Int,
        @Query("difficult") difficulty: String
    ): Response<TriviaResponse>

}