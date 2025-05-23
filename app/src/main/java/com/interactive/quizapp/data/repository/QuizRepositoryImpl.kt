package com.interactive.quizapp.data.repository

import com.interactive.quizapp.data.local.dao.QuestionDao
import com.interactive.quizapp.data.local.entities.QuestionEntity
import com.interactive.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao: QuestionDao
): QuizRepository {

    override suspend fun getQuestions(): Flow<List<QuestionEntity>> = dao.getQuestions()
    override suspend fun saveQuestions(questions: List<QuestionEntity>) = dao.insertQuestions(questions = questions)
    override suspend fun getCategories(): Flow<List<String>> = dao.getCategories()
    override suspend fun getQuestionsByCategory(category: String?): Flow<List<QuestionEntity>> = flow {
        if (category.isNullOrBlank()) {
            val getRandomCategory = dao.getRandomCategory()
            if (getRandomCategory.isNullOrBlank()) {
                emit(emptyList())
            } else {
                emitAll(dao.getQuestionsByCategory(getRandomCategory))
            }
        } else {
            emitAll(dao.getQuestionsByCategory(category))
        }
    }
    override suspend fun updateQuestions(questions: List<QuestionEntity>) = dao.updateQuestions(questions)
    override suspend fun updateQuestion(id: Int, answerIndex: Int) = dao.updateQuestion(id = id, answerIndex = answerIndex)
}