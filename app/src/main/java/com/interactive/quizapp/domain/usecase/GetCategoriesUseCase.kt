package com.interactive.quizapp.domain.usecase

import com.interactive.quizapp.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: QuizRepository
) {

    suspend operator fun invoke(): Flow<List<String>> {
        return repository.getCategories()
    }
}