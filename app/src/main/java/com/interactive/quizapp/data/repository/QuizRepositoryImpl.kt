package com.interactive.quizapp.data.repository

import com.interactive.quizapp.data.local.dao.QuestionDao
import com.interactive.quizapp.data.local.entities.QuestionEntity
import com.interactive.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao: QuestionDao
): QuizRepository {

    override suspend fun getQuestions(): Flow<List<QuestionEntity>> = dao.getQuestions()
    override suspend fun saveQuestions(questions: List<QuestionEntity>) = dao.insertQuestions(questions = questions)
    override suspend fun getCategories(): Flow<List<String>> = dao.getCategories()
    override suspend fun getQuestionsByCategory(category: String): Flow<List<QuestionEntity>> = dao.getQuestionsByCategory(category = category)
    override suspend fun updateQuestions(questions: List<QuestionEntity>) = dao.updateQuestions(questions)
}