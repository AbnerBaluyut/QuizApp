package com.interactive.quizapp.domain.repository

import com.interactive.quizapp.data.local.entities.QuestionEntity
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getQuestions(): Flow<List<QuestionEntity>>
    suspend fun saveQuestions(questions: List<QuestionEntity>)
    suspend fun getCategories(): Flow<List<String>>
    suspend fun getQuestionsByCategory(category: String): Flow<List<QuestionEntity>>
    suspend fun updateQuestions(question: List<QuestionEntity>)
}