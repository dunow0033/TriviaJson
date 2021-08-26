package com.example.triviajson_august9.model.remote

import com.example.triviajson_august9.model.wrapper.Answer
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Question(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    @get:Json(name = "correct_answer")
    val correctAnswer: String,
    @get:Json(name = "incorrect_answers")
    val incorrectAnswers: List<String>
) {

    fun convertToAnswer(): List<Answer> {
        val correctAnswer = Answer(
            this.correctAnswer,
            true
        )

        val listOfWrongAnswers = this.incorrectAnswers.map {
            Answer(it, false)
        }

        return listOfWrongAnswers + correctAnswer
    }
}
