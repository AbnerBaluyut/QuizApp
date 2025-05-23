package com.interactive.quizapp.domain.usecase

import com.interactive.quizapp.data.mapper.toEntity
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.domain.repository.QuizRepository
import javax.inject.Inject

class UpdateQuestionUseCase @Inject constructor(
    private val repository: QuizRepository
) {

    suspend operator fun invoke(id: Int, answerIndex: Int) {
        return repository.updateQuestion(id, answerIndex)
    }
}