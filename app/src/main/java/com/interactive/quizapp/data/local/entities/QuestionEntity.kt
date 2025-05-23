package com.interactive.quizapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val category: String,
    val isMarked: Boolean = false,
    val userAnswerIndex: Int? = null,
)