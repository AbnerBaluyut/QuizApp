package com.interactive.quizapp.domain.model

data class QuestionModel(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val category: String,
    var userAnswerIndex: Int? = null,
)