package com.interactive.quizapp.domain.usecase

import com.interactive.quizapp.data.mapper.toEntity
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.domain.repository.QuizRepository
import javax.inject.Inject


class UpdateQuestionsUseCase @Inject constructor(
    private val repository: QuizRepository
) {

    suspend operator fun invoke(questions: List<QuestionModel>) {
        return repository.updateQuestions(questions.map { it.toEntity() })
    }
}