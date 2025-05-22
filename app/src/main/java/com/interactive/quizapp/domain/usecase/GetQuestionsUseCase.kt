package com.interactive.quizapp.domain.usecase

import com.interactive.quizapp.data.mapper.toModel
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetQuestionsUseCase @Inject constructor(
    private val repository: QuizRepository
) {

    suspend operator fun invoke(): Flow<List<QuestionModel>> {
        return repository.getQuestions().map { entities -> entities.map { it.toModel() } }
    }
}