package com.interactive.quizapp.di

import com.interactive.quizapp.domain.repository.QuizRepository
import com.interactive.quizapp.domain.usecase.GetCategoriesUseCase
import com.interactive.quizapp.domain.usecase.GetQuestionsByCategoryUseCase
import com.interactive.quizapp.domain.usecase.GetQuestionsUseCase
import com.interactive.quizapp.domain.usecase.SaveQuestionsUseCase
import com.interactive.quizapp.domain.usecase.UpdateQuestionUseCase
import com.interactive.quizapp.domain.usecase.UpdateQuestionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSaveQuestionsUseCase(
        repo: QuizRepository
    ): SaveQuestionsUseCase = SaveQuestionsUseCase(repo)

    @Provides
    fun provideGetQuestionsUseCase(
        repo: QuizRepository
    ): GetQuestionsUseCase = GetQuestionsUseCase(repo)

    @Provides
    fun provideGetCategoriesUseCase(
        repo: QuizRepository
    ): GetCategoriesUseCase = GetCategoriesUseCase(repo)

    @Provides
    fun provideGetQuestionsByCategoryUseCase(
        repo: QuizRepository
    ): GetQuestionsByCategoryUseCase = GetQuestionsByCategoryUseCase(repo)

    @Provides
    fun provideUpdateQuestionsUseCase(
        repo: QuizRepository
    ): UpdateQuestionsUseCase = UpdateQuestionsUseCase(repo)

    @Provides
    fun provideUpdateQuestionUseCase(
        repo: QuizRepository
    ): UpdateQuestionUseCase = UpdateQuestionUseCase(repo)
}